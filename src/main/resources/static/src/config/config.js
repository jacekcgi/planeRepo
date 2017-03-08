const appConfig = {
    serverContext: process.env.SERVER_CONTEXT || '/',
    serverPort: process.env.SERVER_PORT || 8080,
    devPort: process.env.DEV_PORT || 3000
};

module.exports = appConfig;
