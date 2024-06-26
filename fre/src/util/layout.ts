export function autoLayout(str) {
    return str.replace(/\s+/g, '\n\n')
}

export function getHui(str) {
    const index = str.lastIndexOf('\n')
    return index > 10 ? str.substring(0,index) : str
}