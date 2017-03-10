// whatever aliases you want; if you do any changes here remember to rebuild your files again!
var path = require('path');
var helpers = require('./config/helpers');

var aliases = {
  'appconfig': helpers.root('src/config/config'),
  'appservices': helpers.root('src/app/services'),
}

module.exports = aliases;
