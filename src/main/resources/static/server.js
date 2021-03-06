var webpack = require('webpack');
var WebpackDevServer = require('webpack-dev-server');
var config = require('./config/webpack.dev');

var app = new WebpackDevServer(webpack(config), {
    contentBase: __dirname,
    publicPath: 'http://localhost:3000/',
    hot: true,
    historyApiFallback: true,
    headers: { 'Access-Control-Allow-Origin': '*' }
});
app.listen('3000', 'localhost', function (err, result) {
    if (err) {
        return console.log(err);
    }
});
