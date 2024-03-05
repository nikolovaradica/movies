<template>
  <div class="container">
    <h1 class="title m-5">Add review for {{ movie.title }}</h1>
    <div class="d-flex justify-content-center align-items-center m-5 p-5">
      <form>
        <div class="mb-5">
          <label for="comment" class="form-label">Review</label>
          <textarea class="form-control" id="comment" required name="comment" v-model="comment"/>
        </div>
        <div class="mb-3">
          <button @click.prevent="saveReview" class="btn">Save</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import MovieDataService from "@/services/MovieDataService";
import router from "@/router";

export default {
  name: "ReviewsForm",
  data() {
    return {
      comment: "",
      movie: "",
    }
  },
  methods: {
    saveReview() {
      let data = {
        comment: this.comment
      }
      MovieDataService.saveReview(this.movie.id, data)
          .then(() => {
            router.push("/movies")
          })
          .catch(err => {
            alert(err)
          })
    },
    getMovie(id) {
      MovieDataService.getById(id)
          .then(response => {
            this.movie = response.data
          })
          .catch(err => {
            alert(err)
          })
    }
  },
  mounted() {
    this.getMovie(this.$route.params.id)
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

  .container {
    margin: auto;
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

  textarea:focus {
    box-shadow: 2px 2px .5px .25px rgba(0, 0, 255, .2);
    border: 1px solid rgba(0, 0, 255, .2);
  }

  textarea {
    width: 25em;
    height: 13em;
  }
</style>