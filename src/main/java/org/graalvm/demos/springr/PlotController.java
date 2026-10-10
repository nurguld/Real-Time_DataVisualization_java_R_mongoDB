package org.graalvm.demos.springr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class PlotController {

    @Autowired
    private PlotService plotService;

    @GetMapping(value = "/plot", produces = "text/html;charset=UTF-8")
    public ResponseEntity<String> plot() {
        String svg = new String(plotService.getLatestSvg(), StandardCharsets.UTF_8);
        svg = svg.replaceFirst("<\\?xml[^>]*\\?>", "");

        String html = "<!DOCTYPE html><html><head><meta charset='UTF-8'>"
                + "<meta http-equiv='refresh' content='1'>"
                + "<title>SWE 307 - Project 1</title></head>"
                + "<body style='margin:0;text-align:center'>"
                + (svg.isBlank() ? "Data is Waiting..." : svg)
                + "</body></html>";

        return ResponseEntity.ok()
                .cacheControl(CacheControl.noStore())
                .body(html);
    }
}