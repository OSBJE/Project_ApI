
document.addEventListener('DOMContentLoaded',  script);

function script(){
    loadComponent("html/layout-header.html", "header-container")
    loadComponent("html/layout-footer.html", "footer-container")
}

//This is how you load in your header and footer
function loadComponent(url, containerID, callback){
    fetch(url)
        .then(response => response.text())
        .then(data =>{document.getElementById(containerID).innerHTML = data;
            if (callback){
                callback();
            }

        })
        .catch(error => console.error("error in loading" + url))

    //this is working. getting the right header and footer in.
    //console.log("did you enter here ?")
}