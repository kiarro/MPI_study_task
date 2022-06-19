
function AddForm()
{
    return(
  <form>
  <label for="name">Имя</label>
  <input type="text" id="name" placeholder="Dragon Name"/>
  <label for="age">Возраст</label>
  <input type="text" id="age" placeholder="Dragon Age"/>
  <input type="submit" value="Добавить"/>
</form>
    );
}