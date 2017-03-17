//fix error TS2304: Cannot find name 'require':
declare function require(string: string): any;
//enviroment variables
declare var process: {
    env: {
        ENV: string,
        SERVER_CONTEXT: string,
        SERVER_PORT: string
    }
};
// jquery & loadsh
declare var _: any;
