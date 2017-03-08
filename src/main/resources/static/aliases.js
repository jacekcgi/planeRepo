// whatever aliases you want; if you do any changes here remember to rebuild your files again!
var path = require('path');

var aliases = {
  'app': path.resolve(__dirname, 'src/app'),
  'appconfig': path.resolve(__dirname, 'src/config/config.js'),
}

module.exports = aliases;
