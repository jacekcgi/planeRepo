const appConfig = {
    serverContext: process.env.SERVER_CONTEXT || '/',
    serverPort: process.env.SERVER_PORT || 8080
};

module.exports = appConfig;
