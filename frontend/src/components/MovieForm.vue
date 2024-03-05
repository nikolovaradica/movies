<template>
  <div class="container">
    <h1 class="title m-5">Add a movie</h1>
    <div class="d-flex justify-content-center align-items-center p-3">
      <form>
        <div class="mb-3">
          <label for="title" class="form-label">Title</label>
          <input type="text" class="form-control" id="title" required name="title" v-model="movie.title">
        </div>
        <div class="mb-3">
          <label for="description" class="form-label">Description</label>
          <textarea class="form-control" id="description" required name="description" v-model="movie.description"/>
        </div>
        <div class="mb-3">
          <label for="genre" class="form-label">Genre</label>
          <input type="text" class="form-control" id="genre" required name="genre" v-model="movie.genre">
        </div>
        <div class="mb-3">
          <label for="year" class="form-label">Year</label>
          <input type="number" class="form-control" id="year" required name="year" v-model="movie.year">
        </div>
        <div class="mb-3">
          <button @click.prevent="saveMovie" class="btn mt-4">Save</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import MovieDataService from "@/services/MovieDataService";
import router from "@/router";

export default {
  name: "MovieForm",
  data() {
    return {
      movie: {
        title: "",
        description: "",
        genre: "",
        year: "",
      },
    }
  },
  methods: {
    saveMovie() {
      let data = {
        title: this.movie.title,
        description: this.movie.description,
        genre: this.movie.genre,
        year: this.movie.year
      }
      MovieDataService.saveMovie(data)
          .then(() => {
            router.push("/movies")
          })
          .catch(err => {
            alert(err)
          })
    }
  }
}
</script>

<style scoped>
  .btn {
    box-shadow: 4px 4px 1px .5px rgba(0, 0, 255, .2);
    border: 1px solid black;
  }

  .btn:hover{
    background-color: rgba(0, 0, 255, .2);
    box-shadow: none;
    transition: all 0.5s ease 0.1s;
  }

  .title {
    display: inline-block;
    position: relative;
    text-decoration: none;
  }

  .title::after {
    content: "";
    position: absolute;
    bottom: 4px;
    left: 0;
    width: 100%;
    height: 13px;
    background-color: rgba(0, 0, 255, .2);
    transform: skew(-20deg) translateX(-5%);
  }

  input:focus, textarea:focus {
    box-shadow: 2px 2px .5px .25px rgba(0, 0, 255, .2);
    border: 1px solid rgba(0, 0, 255, .2);
  }
</style>