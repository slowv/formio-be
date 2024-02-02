export default class LogUtils {
    styleDate = `font-weight: bold;`
    styleValue = 'font-weight: normal;'
    path;
    static #instance;

    constructor(path) {
        this.path = path;
    }

    info(val, threadName) {
        const thread = threadName || 'main';
        console.log(`%c ${this.#buildCommon()} %c| [${thread}] INFO | ${this.path} - ${val}`, this.styleDate, this.styleValue);
    }

    error(err, threadName) {
        const newStyle1 = this.styleDate += 'color: red; background: #ffe6dda1';
        const newStyle2 = this.styleValue += 'color: red; background: #ffe6dda1';
        const thread = threadName || 'main';
        console.log(`%c ${this.#buildCommon()} %c| [${thread}] ERROR | ${this.path} - ${err.title} \n Caused by: ${this.path}: ${err.detail} `, newStyle1, newStyle2);
    }

    json(obj) {
        this.info(JSON.stringify(obj));
    }

    #buildCommon() {
        const d = new Date();
        return ("0" + d.getDate()).slice(-2) + "-" + ("0" + (d.getMonth() + 1)).slice(-2) + "-" +
            d.getFullYear() + " " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2) + ":" + ("0" + d.getSeconds()).slice(-2) + "." + d.getMilliseconds();
    }

    banner(info) {
        console.log(`%c
            __                           ______    _____       __   _____          ___      ____ 
   _____   / /  ____  _      __ _   __  / ____ \\  / ___/      / /  / ___/         <  /     / __ \\
  / ___/  / /  / __ \\| | /| / /| | / / / / __ \`/  \\__ \\  __  / /   \\__ \\          / /     / / / /
 (__  )  / /  / /_/ /| |/ |/ / | |/ / / / /_/ /  ___/ / / /_/ /   ___/ /         / /   _ / /_/ / 
/____/  /_/   \\____/ |__/|__/  |___/  \\ \\__,_/  /____/  \\____/   /____/         /_/   (_)\\____/  
                                       \\____/`, 'font-weight: 700; text-shadow: 3px 3px 0 rgb(217,31,38)');
        console.log(`%c ::  SlowV 😈 :: Running SJS ${info.fw.version} ::`, 'color: #fff; background: green;')
    }

    hostInfo(url, info) {
        console.log("\n----------------------------------------------------------\n\t" +
            `Application '${info.app.name}' is running! Access URLs:\n\t` +
            `Local: \t\t${url.protocol}//localhost:${url.port}\n\t` +
            `Profile(s): \t${info.app.profile}\n----------------------------------------------------------`)
    }


    static getInstance() {
        if (!this.#instance) {
            this.#instance = new LogUtils();
        }
        return this.#instance;
    }
}