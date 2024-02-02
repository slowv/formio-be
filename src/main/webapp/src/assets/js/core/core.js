import AbstractView from "../pages/AbstractView.js";

export function html([first, ...strings], ...values) {
    return values.reduce(
        (acc, cur) => acc.concat(cur, strings.shift()),
        [first]
    )
        .filter(x => x && x !== true || x === 0)
        .join('')
}

export function createStore(reducer) {
    let state = reducer();
    const roots = new Map();

    function render() {
        for (let [root, component] of roots) {
            if ( typeof component === typeof AbstractView) {
                component = new component()
            }
            root.innerHTML = component.getHtml();
        }
    }

    return {
        attach(component, root) {
            roots.set(root, component);
            render();
        },
        connect(selector = state => state) {
            return component => (props, ...args) =>
                component(Object.assign({}, props, selector(state), ...args));
        },
        dispatch(action, ...args) {
            state = reducer(state, action, args);
            render();
        }
    }
}
