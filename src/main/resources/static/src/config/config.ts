export const AppConfig = Object.freeze({
    serverContext: process.env.SERVER_CONTEXT || '/',
    serverPort: process.env.SERVER_PORT || '8080',
    defaultTimeout: 10000,  // default timeout in milis
    defaultNotificationTimeout: 5000,
    languageCookieName: "language",
    deafultLanguage: 'en_EN'
});

