var showContent = (() => {
    const temp = document.getElementsByTagName('template')[0];
    const clon = temp.content.cloneNode(true);
    document.body.appendChild(clon);
})
