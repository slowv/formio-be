import FormService from "../../../service/form.service.js";
import CssRoot from "../../../core/CssRoot.js";

export default class FormDetailComponent extends HTMLElement {
    static tag = "form-detail-component";
    formService;

    content = `
        <div class="form">
            <div id="formio"></div>
        </div> 
    `


    constructor() {
        super();
        this.attachShadow({
            mode: 'open'
        });
        this.onInit();
    }

    onInit() {
        this.formService = FormService.getInstance();
    }

    static observedAttributes() {
        return ["id"];
    }

    connectedCallback() {
        this.render();
    }

    render() {
        this.formService.getForm(this.id).then(res => {
            this.updateView(this.content)
            Formio.createForm(this.shadowRoot.getElementById('formio'), {components: res.components})
        });
    }

    updateView(content) {
        this.shadowRoot.innerHTML = CssRoot.style + content;
    }
}

customElements.define(FormDetailComponent.tag, FormDetailComponent);