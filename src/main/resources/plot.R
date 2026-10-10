library(lattice)

buf <- rep(0, 100)

function(i, v) {
  buf <<- c(buf[-1], v)

  df <- data.frame(x = 0:99, y = buf)

  svg(filename = 'plot.svg', width = 8, height = 6)
  print(xyplot(y ~ x, data = df,
    type = 'l',
    xlim = c(-1, 100),
    xlab = 'Index', ylab = 'Col-17',
    main = 'SWE307 - Col-17',
    panel = function(x, y, ...) {
      panel.grid(h = -1, v = -1)
      panel.xyplot(x, y, type = 'l', col = '#5C3317', lwd = 2)
    }))
  dev.off()

  readChar('plot.svg', file.info('plot.svg')$size)
}