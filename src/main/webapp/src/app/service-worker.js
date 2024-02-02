import LogUtils from "./utils/log.util";


const log = new LogUtils('~.t.a.ServiceWorker')

self.addEventListener('install', e => {
    log.info('Service worker: Installed');
});

self.addEventListener('activate', e => {
    log.info('Service worker: Activated');
});