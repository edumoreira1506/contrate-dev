$(document).ready(() => {
  $('#cellphone').mask('(99)99999-9999')
})

const consultGithub = username => {
  $.ajax({
    datatype: 'json',
    method: 'GET',
    url: `https://api.github.com/users/${username}`,
    success: (result) => {
      console.log(result)
    }
  })
}
