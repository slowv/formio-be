import AppComponent from "./app.component.js";
import match from "./utils/urls.util.js";
import SubmissionComponent from "./module/pages/submission/submission.component.js";
import FormDetailComponent from "./module/pages/form/form-detail.component.js";

const route = [
    {
        path: '/',
        component: AppComponent,
        title: 'Fake Angular app'
    },
    {
        path: '/submission',
        component: SubmissionComponent,
        title: 'Submission'
    },
    {
        path: '/forms/:id',
        component: FormDetailComponent,
        title: 'Form detail'
    }
]

export default class Router extends HTMLElement {
    static #instance;
    outlet;

    constructor() {
        super();
        this.outlet = document.querySelector("route-outlet")
    }

    get root() {
        return window.location.pathname;
    }

    get routes() {
        return route;
    }

    connectedCallback() {
        this.updateLinks();
        this.navigate(window.location.pathname);

        window.addEventListener("popstate", this._handlePopstate);
    }

    disconnectedCallback() {
        window.removeEventListener("popstate", this._handlePopstate);
    }

    _handlePopstate = () => {
        this.navigate(window.location.pathname);
    };

    updateLinks() {
        document.querySelectorAll("[routerLink]").forEach(link => {
            const target = link.getAttribute("routerLink");
            link.onclick = (e) => {
                e.preventDefault();
                this.navigate(target);
            }
        });
    }

    navigate(url) {
        const matchedRoute = match(this.routes, url);
        if (matchedRoute !== null) {
            this.activeRoute = matchedRoute;
            window.history.pushState(null, null, url);
            this.update();
        }
    }

    // Update DOM
    update() {
        const {
            component,
            title,
            params = {}
        } = this.activeRoute;

        if (component) {
            // Remove all child nodes under outlet element
            while (this.outlet.firstChild) {
                this.outlet.removeChild(this.outlet.firstChild);
            }

            const updateView = () => {
                const view = document.createElement(component.tag);
                document.title = title || document.title;
                for (let key in params) {
                    /**
                     * all dynamic param value will be passed
                     * as the attribute to the newly created element
                     * except * value.
                     */
                    if (key !== "*") view.setAttribute(key, params[key]);
                }
                this.outlet.appendChild(view);
                // Update the route links once the DOM is updated
                this.updateLinks();
            };

            updateView();
        }
    }

    static getInstance()  {
        if (!this.#instance) {
            this.#instance = new Router();
        }
        return this.#instance;
    }
}
customElements.define("wc-router", Router);