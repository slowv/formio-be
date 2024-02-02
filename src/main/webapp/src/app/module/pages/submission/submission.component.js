
import App from "../../../app.js";
import LogUtils from "../../../utils/log.util.js";

export default class SubmissionComponent extends HTMLElement {
    static tag = "submission-component";
    #log = LogUtils.getInstance();

    constructor() {
        super();
        const shadowRoot = this.attachShadow({
            mode: 'open'
        });
        this.onInit();
    }

    onInit() {
    }

    render() {
        let content = '';

        content += `
            <div> Tag: ${SubmissionComponent.tag}, Page submission</div>
        `;
        this.updateView(content)
    }

    updateView(content = '') {
        this.shadowRoot.innerHTML = content;
    }

    connectedCallback() {
        this.#log.info(`${SubmissionComponent.tag} is mounted!`)
        this.render();
        this.shadowRoot.querySelectorAll(".user-card").forEach(card => {
            card.onclick = (e) => {
                const uri = e.target.getAttribute("routerLink");
                App.router.navigate(uri);
            }
        });
    }
}

customElements.define(SubmissionComponent.tag, SubmissionComponent);