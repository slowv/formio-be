export default class SubmissionComponent extends HTMLElement {
    static tag = "submission-component";

    constructor() {
        super();
        this.onInit();
    }

    onInit() {
    }

    updateView(content = '') {
        this.shadowRoot.innerHTML = content;
    }

    connectedCallback() {
        this.attachShadow({
            mode: 'open'
        })
        let content = '';
        content += `
            <div> Tag: ${SubmissionComponent.tag}, Page submission</div>
        `;
        this.updateView(content)
    }
}

customElements.define(SubmissionComponent.tag, SubmissionComponent);