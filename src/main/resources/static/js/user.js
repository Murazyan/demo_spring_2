let userBlockPage = 1;

async function updateUserTable(pageSize, pageNumber) {
    const response = await fetch('http://localhost:8080/admin/user-table?' + new URLSearchParams({
        userPage: pageNumber,
        userSize: pageSize
    }))

    const data = await response.text();
    const userBlock = document.getElementById('users-block');
    userBlock.innerHTML = data;
    userBlockPage = pageNumber;
}


function deleteUser(userId, rowNumber) {
    fetch('http://localhost:8080/admin/user/' + userId, {
        method: 'DELETE'
        // body: JSON.stringify({ "id": userId })
    })
        .then(response => {
            if (response.status == 200) {
                const children = document.getElementById('users-block-body').children;
                console.log(children)
                updateUserTable(4, userBlockPage)
            }
        })
}


function updateUser(userId, rowNumber) {
    fetch('http://localhost:8080/admin/user/' + userId, {
        method: 'PUT',
        body: JSON.stringify({
            "id": userId,
            'locked': document.getElementById('user-locked' + userId).value
        }),
        headers: {
            "Content-Type": "application/json",
        }
    })
        .then(response => {
            if (response.status != 200) {
                alert("error")
            }
        })

}
