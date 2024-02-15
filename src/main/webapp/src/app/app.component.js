import FormService from "./service/form.service.js";
import {style} from "./app.css.js";
import {useNavigate} from "./core/ApplicationContext.js";
import CssRoot from "./core/CssRoot.js";


export default class AppComponent extends HTMLElement {
    static tag = "app-component";
    constructor() {
        super();
        this.onInit();
    }

    onInit() {
        this._formService = FormService.getInstance();
        this.toDetailPage = this.toDetail;
    }

    connectedCallback() {
        let shadowRoot = this.attachShadow({
            mode: 'open'
        });
        let content = '';
        this._formService.getForms().then(res => {
            content += `<ul class="list-group">`
            res.forEach(item => {
                content += `
                    <li class="list-group-item form-item" routerLink='/forms/${item.id}'>
                        ${item.title}
                    </li>
                `
            })
            content += '</ul>'
            shadowRoot.innerHTML = CssRoot.style + content;
        }).then(() => this.addEvents(shadowRoot));
    }

    disconnectedCallback() {
        this.shadowRoot.removeEventListener('click', this.toDetailPage)
    }

    toDetail(link) {
        console.log(link)
        useNavigate().navigate(link);
    }

    addEvents(shadowRoot) {
        shadowRoot.querySelectorAll('[routerLink]')
            .forEach(elm => {
                let link = elm.getAttribute('routerLink');
                elm.addEventListener('click', () => this.toDetailPage(link));
            })
    };
}

customElements.define(AppComponent.tag, AppComponent);