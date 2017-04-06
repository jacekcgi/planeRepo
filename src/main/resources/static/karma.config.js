var webpackConfiguration = require('./config/webpack.test.js');

module.exports = function (config) {
  config.set({
    captureConsole: true,
    browserConsoleLogOptions: {
      level: 'log'
    },
    basePath: '',
    frameworks: ['jasmine'],
    mime: {
      'text/x-typescript': ['ts', 'tsx']
    },
    files: [
      { pattern: './src/test/main.js', watched: false }
    ],
    exclude: [
    ],
    preprocessors: {
      './src/test/main.js': ['webpack', 'sourcemap']
    },
    webpack: webpackConfiguration,
    reporters: ['progress'],
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: false,
    browsers: ['Chrome'],
    singleRun: true,
    concurrency: Infinity,
  })
}
