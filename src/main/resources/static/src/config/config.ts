//export var serverContext: string = process.env.SERVER_CONTEXT || '/';
//export var serverPort: string = process.env.SERVER_PORT || '8080';

export const AppConfig = Object.freeze({
     serverContext: process.env.SERVER_CONTEXT || '/',
     serverPort: process.env.SERVER_PORT || '8080'
 });

