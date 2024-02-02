// Import config service here
import App from "../../app/app.js";

const config = {
    cache: {
        isActive: true,
        path: '/src/app/service-worker.js',
        assets: [
            // '/assets/css/index.js'
        ]
    },
    debug: true,
    image: {
        lazy: true
    }
};


// Start service app
App.initialize(config).then();

