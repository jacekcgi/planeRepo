var ExtractTextPlugin = require('extract-text-webpack-plugin');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var helpers = require('./helpers');
var aliases = require('../aliases');

module.exports = {
  devtool: 'eval',
  entry: {
      'polyfills': './src/polyfills.ts',
      'vendor': './src/vendor.ts',
      'app': ['webpack-dev-server/client?http://localhost:3000/',
              'webpack/hot/only-dev-server',
              './src/main.ts']
    },

  resolve: {
      modules: [helpers.root('src'), "node_modules"],
      extensions: ['.ts', '.js'],
      alias: aliases
    },

  module: {
      rules: [
        {
          test: /\.ts$/,
          loaders: [
            {
              loader: 'awesome-typescript-loader',
              options: { configFileName: helpers.root('src', 'tsconfig.json') }
            } , 'angular2-template-loader'
          ]
        },
        {
          test: /\.html$/,
          loader: 'html-loader'
        },
        {
          test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|ico)$/,
          loader: 'file-loader?name=assets/[name].[hash].[ext]'
        },
        {
          test: /\.css$/,
          exclude: helpers.root('src', 'app'),
          loader: ExtractTextPlugin.extract({ fallbackLoader: 'style-loader', loader: 'css-loader?sourceMap' })
        },
        {
          test: /\.css$/,
          include: helpers.root('src', 'app'),
          loader: 'raw-loader'
        }
      ]
  },

  output: {
    path: helpers.root('built'),
    publicPath: 'http://localhost:3000/built/',
    filename: '[name].js',
    chunkFilename: '[id].chunk.js'
  },

  plugins: [
    new ExtractTextPlugin('[name].css'),
    new webpack.HotModuleReplacementPlugin(),
    // Workaround for angular/angular#11580
        new webpack.ContextReplacementPlugin(
          // The (\\|\/) piece accounts for path separators in *nix and Windows
          /angular(\\|\/)core(\\|\/)(esm(\\|\/)src|src)(\\|\/)linker/,
          helpers.root('./src'), // location of your src
          {} // a map of your routes
        ),
        new webpack.optimize.CommonsChunkPlugin({
          name: ['app', 'vendor', 'polyfills']
        })
  ]
};
