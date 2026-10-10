package org.graalvm.demos.springr;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class PlotService {

    @Autowired
    private ProjectDataRepository repository;

    private Context context;
    private Value plotFunction;
    private int index = 0;
    private volatile byte[] latestSvg = new byte[0];


    private void init() {
        try (InputStream in = new ClassPathResource("plot.R").getInputStream()) {
            String rCode = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            context = Context.newBuilder("R").allowAllAccess(true).build();
            plotFunction = context.eval(Source.create("R", rCode));
        } catch (IOException e) {
            throw new RuntimeException("plot.R couldn't read ", e);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void tick() {
        try {
            long total = repository.count();
            if (total == 0) return;
            if (context == null) init();


            List<ProjectData> page = repository
                    .findAll(PageRequest.of(index, 1))
                    .getContent();
            if (page.isEmpty() || page.get(0).getCol17() == null) {
                index = 0;
                return;
            }

            double value = page.get(0).getCol17();
            System.out.println("row " + index + " -> " + value);


            String svg = plotFunction.execute(index, value).asString();
            latestSvg = svg.getBytes(StandardCharsets.UTF_8);

            index = (index + 1) % (int) total;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getLatestSvg() {
        return latestSvg;
    }

    @PreDestroy
    public void close() {
        if (context != null) context.close();
    }
}
