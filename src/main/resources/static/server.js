var webpack = require('webpack');
var WebpackDevServer = require('webpack-dev-server');
var config = require('./config/webpack.dev');
var appConfig = require('./src/config/config')

var app = new WebpackDevServer(webpack(config), {
    contentBase: __dirname,
    publicPath: 'http://localhost:' + '3000' + '/built/',
    hot: true,
    historyApiFallback: true,
    headers: { 'Access-Control-Allow-Origin': '*' }
});
app.listen(appConfig.devPort, 'localhost', function (err, result) {
    if (err) {
        return console.log(err);
    }
});
