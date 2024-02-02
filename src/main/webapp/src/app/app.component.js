import LogUtils from "./utils/log.util.js";
import FormService from "./service/form.service.js";
import {style} from "./app.css.js";
import App from "./app.js";


export default class AppComponent extends HTMLElement {
    static tag = "app-component";
    constructor() {
        super();
        this.attachShadow({
            mode: 'open'
        });
        this.onInit();
    }

    onInit() {
        this._formService = FormService.getInstance();
        this._navigateTo = this.toDetail.bind(this, "");
        this._log = new LogUtils('~s.app.AppComponent');
    }

    connectedCallback() {
        this.render();
        // $('[routerLink]').forEach(card => {
        //     const uri = card.getAttribute("routerLink");
        //     card.onclick = (e) => {
        //         e.preventDefault();
        //         App.router.navigate(uri);
        //     }
        // });
    }

    disconnectedCallback() {
        this.shadowRoot.querySelectorAll('[routerLink]')
            .forEach(elm => elm.removeEventListener('click', this._navigateTo))
    }

    toDetail(event, formId) {
        console.log(event)
        console.log(formId)
    }


    render() {
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
            this.updateView(content)
        });
    }

    updateView(content) {
        this.shadowRoot.innerHTML = style + content;
        this.shadowRoot.querySelectorAll('[routerLink]')
            .forEach(elm => {
                let link = elm.getAttribute('routerLink');
                elm.addEventListener('click', () => this._navigateTo(this, link));
            })
    };
}

customElements.define(AppComponent.tag, AppComponent);