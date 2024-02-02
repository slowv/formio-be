export const html = ([first, ...strings], ...values) => {
    return values.reduce(
        (acc, cur) => acc.concat(cur.strings.shift()),
        [first]
    ).filter(x => x && x !== true || x === 0).join('')
}

export const createStore = (reducer) => {
    let state = reducer()
    // @ts-ignore
    const roots = new Map()

    function render() {
        for(const [root, pages] of roots) {
            root.innerHTML = pages()
        }
    }
}