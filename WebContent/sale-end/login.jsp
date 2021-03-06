<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>記事本</title>

  <!-- Bootstrap 4 CSS 的 CDN -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
    crossorigin="anonymous">

  <!-- FirebaseUI 預設的 CDN -->
  <!-- <script src="https://cdn.firebase.com/libs/firebaseui/3.1.1/firebaseui.js"></script>
  <link type="text/css" rel="stylesheet" href="https://cdn.firebase.com/libs/firebaseui/3.1.1/firebaseui.css" /> -->

  <!-- FirebaseUI 在地化的 CDN -->
  <!-- https://firebase.google.com/docs/auth/web/firebaseui -->
  <!-- https://github.com/firebase/firebaseui-web#localized-widget -->
  <script src="https://www.gstatic.com/firebasejs/ui/3.4.0/firebase-ui-auth__zh_tw.js"></script>
  <link type="text/css" rel="stylesheet" href="https://www.gstatic.com/firebasejs/ui/3.4.0/firebase-ui-auth.css" />
  <style>
    body {
      font-family: Microsoft JhengHei;
    }
  </style>
</head>

<body>
  <!-- Vue實例的掛載點 -->
  <div id="app" class="container">
    <div class="row">
      <div class="col text-center">
        <h3>Firebase Authectication 認證</h3>
        <p>使用 FirebaseUI</p>
        <div id="firebaseui-auth-container"  v-if="!issignedin"></div>
      </div>
    </div>
    <hr />
    <div class="row">
      <div class="col text-center">
        <div v-if="issignedin">
          <div class='text-center'>
          
            <img :src='photoURL' class='img-fluid'>
            <div id="name">{{ displayname }}</div>
            <div id="email">{{ email }}</div>
            <div id="phone">{{ phone }}</div>
            
            
          </div>
          <p class='text-center'>
            <button @click='signOut' type='button' class='btn btn-success'>登出</button>
          </p>
        </div>
        <div v-else>
          <h4>您已登出...</h4>
        </div>
      </div>
    </div>
  </div>


  <!-- Bootstrap 4 JavaScript 的 CDN -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
    crossorigin="anonymous"></script>


  <!-- Vue 的 CDN -->
  <script src="https://unpkg.com/vue/dist/vue.js"></script>
  <!-- Vue實例的程式碼 -->
  <script src="https://www.gstatic.com/firebasejs/5.6.0/firebase.js"></script>
  <script>
    // Initialize Firebase
    var config = {
    		apiKey: "AIzaSyDjd7VXUjKrS9ZvvKPvmCDkCisTtWKT7Jc",
    	    authDomain: "tea103g5.firebaseapp.com",
    	    projectId: "tea103g5",
    	    storageBucket: "tea103g5.appspot.com",
    	    messagingSenderId: "392993031553",
    	    databaseURL:"https://tea103g5-default-rtdb.firebaseio.com/"
    };
    firebase.initializeApp(config);

    var ui = new firebaseui.auth.AuthUI(firebase.auth());

    new Vue({
      el: '#app',
      data: {
        issignedin: false,
        displayname: '',
        photoURL: '',
        email: '',
        phone:''
      },
      mounted() {
        ui.start('#firebaseui-auth-container', this.getUiConfig());

        firebase.auth().onAuthStateChanged((user) => {
          if (user) {
            this.issignedin = true
            this.displayname = user.displayName
            this.phone = user.phoneNumber
            this.email = user.email
            this.photoURL = user.photoURL
            console.log("Sample log");
            window.location.replace("saleEndIndex.jsp");
          }
        })
      },
      methods: {
        getUiConfig() {
          return {
            'callbacks': {
              // Called when the user has been successfully signed in.
              'signInSuccess': function (user, credential, redirectUrl) {
                this.issignedin = true
                this.displayname = user.displayName
                this.phone = user.phoneNumber
                this.email = user.email
                this.photoURL = user.photoURL
				
                // Do not redirect.
                return false;
              }
            },
            // Opens IDP Providers sign-in flow in a popup.
            'signInFlow': 'popup',
            'signInOptions': [
              // The Provider you need for your app. We need the Phone Auth
              firebase.auth.GoogleAuthProvider.PROVIDER_ID,
              firebase.auth.EmailAuthProvider.PROVIDER_ID,
              {
                provider: firebase.auth.PhoneAuthProvider.PROVIDER_ID,
                recaptchaParameters: {
                  //size: getRecaptchaMode()
                  type: 'image',
                  size: 'invisible',
                  badge: 'bottomleft'
                },
                defaultCountry: 'TW',
              }
            ],

            // Terms of service url.
            'tosUrl': 'https://www.google.com',
            // Privacy policy url.
            'privacyPolicyUrl': 'https://www.google.com'
          };
        },
        signOut() {
          firebase.auth().signOut().then(() => {
            this.user = null
            this.displayName = ''
            this.email = ''
            this.issignedin = false
            console.log('sign out...')
          }).catch(err => console.log(error))

          ui.start('#firebaseui-auth-container', this.getUiConfig());
        }
      }
    })
  </script>
</body>

</html>