$(document).ready(() => {
  $('#cellphone').mask('(99)99999-9999')

  $('.Modal__background').click(() => {
    closeModal()
  })

  $('#close-modal').click(() => {
    closeModal
  })
})

const showModal = () =>
  $('#modal-github').removeClass('hidden')

const closeModal = () =>
  $('#modal-github').addClass('hidden')

const makeList = user =>
`
<li>
  ${user.login}
</li>
<li>
  ${user.company}
</li>
<li>
  ${user.bio}
</li>
<li>
  ${user.public_repos} repositórios
</li>
<li>
  ${user.location}
</li>
<li>
  <a target="_blank" href="${user.html_url}">
    Checar
  </a>
</li>
`

const consultGithub = username => {
  $.ajax({
    datatype: 'json',
    method: 'GET',
    url: `https://api.github.com/users/${username}`,
    success: (result) => {
      document.getElementById('user-image').src = result.avatar_url
      const html = makeList(result)
      $('#modal-list').html(html)
      showModal()
    }
  })
}
