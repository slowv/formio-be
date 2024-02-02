
import Router from "./routing.module.js";
import LogUtils from "./utils/log.util.js";

export default class App {
    static router;
    static config = {
        fw: {
            name: 'sjs',
            version: '1.0.0'
        },
        app: {
            name: 'Form1o - CIMB',
            version: '1.0.0@SNAP-SHOT',
            profile: 'dev'
        }
    };
    static log = new LogUtils('~~.t.a.App');

    static async initialize(config) {
        this.log.banner(this.config)
        this.config = Object.assign(this.config, config);
        if (this.config.debug) {
            this.log.info(JSON.stringify(config));
        }

        if (this.config.image.lazy) {
            this.#handleImageLazy();
        }

        if (this.config.cache.isActive) {
            this.#handleCache();
        }

        this.log.hostInfo(new URL(window.location), this.config);
        this.router = new Router()
    }

    static #handleCache() {
        // if ('serviceWorker' in navigator) {
        //     window.addEventListener('load', () => {
        //         navigator.serviceWorker
        //             .register(this.config.cache.path)
        //             .then(() => this.log.info('Service worker registered'))
        //             .then(() => {
        //                 this.log.error({title:`Save assets to cache failure.`,
        //                     detail: `File '${this.config.cache.assets[0]}' not found`});
        //             })
        //             .catch((err) => this.log.error(`Service worker: ERROR ${err}`));
        //     });
        // }
    }

    static #handleImageLazy() {
        this.log.info('Lazy load image is ACTIVE');
    }
}