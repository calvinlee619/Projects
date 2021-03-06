<%--
  Created by IntelliJ IDEA.
  User: kangw
  Date: 7/4/16
  Time: 7:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Saveur - Restaurant Template by DotRex</title>

  <!-- Favicons
  ================================================== -->
  <link rel="shortcut icon" href="http://dotrex.co/theme-preview/saveur-preview/favicon.ico" type="image/x-icon">
  <link rel="icon" href="http://dotrex.co/theme-preview/saveur-preview/favicon.ico" type="image/x-icon">
  <!-- / Favicons
  ================================================== -->

  <!-- >> CSS
  ============================================================================== -->
  <link href="http://dotrex.co/theme-preview/saveur-preview/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Google Web Fonts -->
  <link href='https://fonts.googleapis.com/css?family=Playfair+Display:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
  <!-- Font Awesome -->
  <link href="http://dotrex.co/theme-preview/saveur-preview/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <!-- animate.css -->
  <link href="http://dotrex.co/theme-preview/saveur-preview/vendor/animate.css" rel="stylesheet">
  <!-- Nivo Lightbox -->
  <link href="http://dotrex.co/theme-preview/saveur-preview/vendor/nivo-lightbox/nivo-lightbox.css" rel="stylesheet">
  <link rel="stylesheet" href="http://dotrex.co/theme-preview/saveur-preview/vendor/nivo-lightbox/themes/default/default.css" type="text/css" />
  <!-- Main Styles -->
  <link href="http://dotrex.co/theme-preview/saveur-preview/css/styles.css" rel="stylesheet">

  <!-- >> /CSS
  ============================================================================== -->
</head>
<body class="">

<!-- Page Loader
========================================================= -->
<div class="loader-container" id="page-loader">
  <div class="loading-wrapper loading-wrapper-hide">
    <div class="loader-animation" id="loader-animation">
      <div class="sk-folding-cube">
        <div class="sk-cube1 sk-cube"></div>
        <div class="sk-cube2 sk-cube"></div>
        <div class="sk-cube4 sk-cube"></div>
        <div class="sk-cube3 sk-cube"></div>
      </div>
    </div>
    <!-- Edit With Your Name -->
    <div class="loader-name" id="loader-name">
      <img src="http://dotrex.co/theme-preview/saveur-preview/img/logo.png" alt="">
    </div>
    <!-- /Edit With Your Name -->
  </div>
</div>
<!-- /End of Page loader
========================================================= -->

<!-- Page Container -->
<div class="container">

  <!-- Header
  ================================================== -->
  <header id="header">
    <nav class="navbar">


      <div class="menu-wrapper">

        <!-- Navbar Header -->
        <div class="navbar-header">
          <!-- Collapse Button - Responsive -->
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
            <i class="fa fa-bars"></i>
          </button>
          <!-- /Collapse Button - Responsive-->
          <!-- logo -->
          <a class="navbar-brand" href="index.html">
            <img src="http://dotrex.co/theme-preview/saveur-preview/img/logo.png" alt="">
          </a>
          <!-- /logo -->
        </div>
        <!-- / Navbar Header -->

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-main-collapse" id="#options">
          <ul class="nav navbar-nav">
            <li>
              <a href="#home">Home</a>
            </li>
            <li>
              <a href="#about" >About</a>
            </li>
            <li>
              <a href="#menu">Menu</a>
            </li>
            <li>
              <a href="/recommendation" >Recommendation</a>
            </li>
            <li>
              <a href="#contact">Contact</a>
            </li>
          </ul>
        </div>
        <!-- /.navbar-collapse -->

      </div>
    </nav>
  </header>
  <!-- / Header
  ================================================== -->


  <!-- Main Content
  ================================================== -->
  <section class="main-content" >

    <!-- Page Elements -->
    <div class="isotope-filter" id="isotope-filter">

      <!-- ======== Element: Profile ======== -->
      <div class="element element-intro home" id="element-intro">
        <div class="element-wrapper">
          <!-- Intro- Element content -->
          <div class="intro-item" id="intro-item1" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/intro2.jpg');">
            <!-- Carousel Item Mask -->
            <div class="intro-item-mask">
              <div class="intro-item-content">
                <h2 class="intro-item-title">Welcome to <strong>Saveur</strong></h2>
                <div class="intro-item-bar"></div>
                <p class="intro-item-title2">Discover the best of world cuisine </p>
                <div class="intro-item-buttons">
                  <a href="#about" class="btn btn-default btn-rayen intro-btn-left isotope-link" data-text="See more"><span>See More</span></a>
                  <a href="#menu" class="btn btn-default btn-rayen intro-btn-right isotope-link" data-text="Our Menu"><span>Our Menu</span></a>
                </div>
              </div>
            </div>
            <!-- Item Mask -->
          </div>
          <!-- /Intro element content -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: About Resume ======== -->
      <div class="element element-big-image element-big-image-home home">
        <div class="element-wrapper">
          <img src="http://dotrex.co/theme-preview/saveur-preview/img/home-featured.jpg" alt="">
          <!-- mask -->
          <div class="element-big-image-mask">
            <div class="mask-content">
              <h3 class="mask-content-title">"Bringing <strong>taste</strong> and <br/><strong>elegance</strong> in the same place..."</h3>
              <p><strong>Saveur</strong>. 212 Ave Lorem, London, UK</p>
              <p>Since November 05, 2002</p>
            </div>
          </div>
          <!-- /mask -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: About Resume ======== -->
      <div class="element element-big-content about">
        <div class="element-wrapper">
          <div class="element-big-content-ico"><img src="http://dotrex.co/theme-preview/saveur-preview/img/ico1.svg" alt=""></div>
          <h2 class="element-big-content-title about-title">Welcome to <strong>Saveur</strong></h2>
          <p> We have the best of <strong>international cuisine</strong>. Come visit us and discover! we are in a wonderful place, at by 212 Ave Lorem, London, UK.</p>
          <a href="#contact" class="btn btn-default btn-rayen isotope-link" data-text="Let's Go!"><span>Get Directions <i class="fa fa-long-arrow-right"></i></span></a>
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Team Member ======== -->
      <div class="element element-content element-team-member about">
        <div class="element-wrapper">
          <!-- Item Image -->
          <div class="menu-featured-image" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/team/team4.jpg');"></div>
          <!-- /Item image -->
          <!-- our staff -->
          <div class="team-member-staff">Our Staff</div>
          <!-- /our staff -->
          <!-- Menu Element Mask -->
          <div class="team-member-mask-wrapper">
            <div class="team-member-mask">
              <!-- Team Member Name -->
              <div class="team-member-text">
                <h3 class="team-member-title">James Rex</h3>
                <p class="team-member-description">Cuisine Chef</p>
              </div>
              <!-- /Team Member Name -->
            </div>
          </div>
          <!-- /Menu Element Mask -->
        </div>
      </div>
      <!-- ======== / Element: Team Member ======== -->


      <!-- ======== Element: About Picture ======== -->
      <div class="element element-about-picture about">
        <div class="element-wrapper">
          <!-- link box -->
          <a href="http://dotrex.co/theme-preview/saveur-preview/img/about-picture3.jpg" class="about-picture-mask nivobox" data-lightbox-gallery="about">
            <div class="about-picture-content">
              <i class="fa fa-eye"></i>
            </div>
          </a>
          <!-- /link box -->
          <!-- img -->
          <img src="http://dotrex.co/theme-preview/saveur-preview/img/about-picture3.jpg" class="responsive-image" alt="">
          <!-- /img -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: About Picture ======== -->
      <div class="element element-about-picture about">
        <div class="element-wrapper">
          <!-- link box -->
          <a href="http://dotrex.co/theme-preview/saveur-preview/img/about-picture.jpg" class="about-picture-mask nivobox" data-lightbox-gallery="about">
            <div class="about-picture-content">
              <i class="fa fa-eye"></i>
            </div>
          </a>
          <!-- /link box -->
          <!-- img -->
          <img src="http://dotrex.co/theme-preview/saveur-preview/img/about-picture.jpg" class="responsive-image" alt="">
          <!-- /img -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Team Member ======== -->
      <div class="element element-content element-team-member about">
        <div class="element-wrapper">
          <!-- Item Image -->
          <div class="menu-featured-image" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/team/team3.jpg');"></div>
          <!-- /Item image -->
          <!-- our staff -->
          <div class="team-member-staff">Our Staff</div>
          <!-- /our staff -->
          <!-- Menu Element Mask -->
          <div class="team-member-mask-wrapper">
            <div class="team-member-mask">
              <!-- Team Member Name -->
              <div class="team-member-text">
                <h3 class="team-member-title">Ryan Rex</h3>
                <p class="team-member-description">Cuisine Chef</p>
              </div>
              <!-- /Team Member Name -->
            </div>
          </div>
          <!-- /Menu Element Mask -->
        </div>
      </div>
      <!-- ======== / Element: Team Member ======== -->

      <!-- ======== Element: Service ======== -->
      <div class="element element-content element-services  home about">
        <div class="element-wrapper">
          <!-- element box -->
          <div class="element-box">
            <div class="element-box-bg">
              <!-- box title -->
              <h2 class="element-box-title"><span>Restaurant</span></h2>
              <!-- /box-title -->
              <!-- Service Item-->
              <div class="service-item">
                <h2 class="service-title">Opening hours</h2>
                <div class="service-description">
                  <dl class="dl-horizontal hours-list">
                    <dt>Monday</dt>
                    <dd>11am - 22pm</dd>
                    <dt>Tuesday</dt>
                    <dd>11am - 22pm</dd>
                    <dt>Wednesday</dt>
                    <dd>15pm - 23pm</dd>
                    <dt>Thursday</dt>
                    <dd>15pm - 23pm</dd>
                    <dt>Friday</dt>
                    <dd>15pm - 23pm</dd>
                    <dt>Saturday</dt>
                    <dd>11am - 22pm</dd>
                    <dt>Sundway</dt>
                    <dd>closed</dd>
                  </dl>
                </div>
              </div>
              <!-- /Service Item -->
            </div>
          </div>
          <!-- /element-box -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Menu - Featured Item ======== -->
      <div class="element element-menu-featured menu">
        <div class="element-wrapper">
          <!-- Item Image -->
          <div class="menu-featured-image" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/products/p1.jpg');"></div>
          <!-- /Item image -->
          <!-- Menu Element Mask -->
          <div class="menu-featured-mask-wrapper">
            <div class="menu-featured-mask">
              <!-- row -->
              <div class="row menu-featured-title-wrapper">
                <!-- item title -->
                <div class="col-xs-8">
                  <h3 class="menu-featured-title">Pepperoni Pizza</h3>
                </div>
                <!-- /item title -->
                <!-- item price -->
                <div class="col-xs-4">
                  <span class="menu-featured-price">$ 9.95</span>
                </div>
                <!-- /item price -->
              </div>
              <!-- /row -->
              <!-- Item Description -->
              <div class="menu-featured-description">
                <p>Type the description of the dish and show how it can be special. Make it look delicious!</p>
                <p class="menu-featured-text-small"><i><strong>Ingredients:</strong> Verno slices, Dolre oil, Green onions, Mushroom, Tomatoes</i></p>
              </div>
              <!-- /Item Description -->
            </div>
          </div>
          <!-- /Menu Element Mask -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Menu - Featured Item ======== -->
      <div class="element element-menu-featured home menu">
        <div class="element-wrapper">
          <!-- Item Image -->
          <div class="menu-featured-image" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/products/p8.jpg');"></div>
          <!-- /Item image -->
          <!-- Menu Element Mask -->
          <div class="menu-featured-mask-wrapper">
            <div class="menu-featured-mask">
              <!-- row -->
              <div class="row menu-featured-title-wrapper">
                <!-- item title -->
                <div class="col-xs-8">
                  <h3 class="menu-featured-title">Barbecue Beef</h3>
                </div>
                <!-- /item title -->
                <!-- item price -->
                <div class="col-xs-4">
                  <span class="menu-featured-price">$ 8.50</span>
                </div>
                <!-- /item price -->
              </div>
              <!-- /row -->
              <!-- Item Description -->
              <div class="menu-featured-description">
                <p>Type the description of the dish and show how it can be special. Make it look delicious!</p>
                <p class="menu-featured-text-small"><i><strong>Ingredients:</strong> Verno slices, Dolre oil, Green onions, Mushroom, Tomatoes</i></p>
              </div>
              <!-- /Item Description -->
            </div>
          </div>
          <!-- /Menu Element Mask -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Menu - Featured Item ======== -->
      <div class="element element-menu-featured home menu">
        <div class="element-wrapper">
          <!-- Item Image -->
          <div class="menu-featured-image" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/products/p4.jpg');"></div>
          <!-- /Item image -->
          <!-- Menu Element Mask -->
          <div class="menu-featured-mask-wrapper">
            <div class="menu-featured-mask">
              <!-- row -->
              <div class="row menu-featured-title-wrapper">
                <!-- item title -->
                <div class="col-xs-8">
                  <h3 class="menu-featured-title">Spaghetti</h3>
                </div>
                <!-- /item title -->
                <!-- item price -->
                <div class="col-xs-4">
                  <span class="menu-featured-price">$ 7.75</span>
                </div>
                <!-- /item price -->
              </div>
              <!-- /row -->
              <!-- Item Description -->
              <div class="menu-featured-description">
                <p>Type the description of the dish and show how it can be special. Make it look delicious!</p>
                <p class="menu-featured-text-small"><i><strong>Ingredients:</strong> Verno slices, Dolre oil, Green onions, Mushroom, Tomatoes</i></p>
              </div>
              <!-- /Item Description -->
            </div>
          </div>
          <!-- /Menu Element Mask -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Menu List ======== -->
      <div class="element element-menu-list menu">
        <div class="element-wrapper">
          <h2 class="element-big-content-title"><strong>Appetizers</strong> Menu</h2>
          <ul class="restaurant-menu-list">
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>The Space Chicken</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $14
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Here is the description of the menu item.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>Thata's Spaghetti</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $18
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Here is the description of the other menu item.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>Salmon Filet</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $22
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Here also is another description of item.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>Roasted Chicken</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $11
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Here is the description of the menu item.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>The Space Chicken</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $14
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Here is the description of the menu item.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>Pancakes</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $13
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Item Description. Make it looks delicious!</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>Special Spaghetti</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $18
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>item description here.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
          </ul>
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Menu List ======== -->
      <div class="element element-menu-list menu">
        <div class="element-wrapper">
          <h2 class="element-big-content-title"><strong>Main</strong> dishes</h2>
          <ul class="restaurant-menu-list">
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>The Space Chicken</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $14
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Here is the description of the menu item.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>Spaghetti</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $18
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Here is the description of the other menu item.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>Salmon Filet</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $22
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Here also is another description of item.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>Roasted Chicken</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $11
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Here is the description of the menu item.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>The Space Chicken</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $14
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Here is the description of the menu item.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>Pancakes</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $13
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Item Description. Make it looks delicious!</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
            <!-- item -->
            <li>
              <!-- item title -->
              <div class="restaurant-menu-title">
                <!-- item name -->
                <div class="restaurant-menu-name">
                  <span>Special Spaghetti</span>
                </div>
                <!-- /item name -->
                <div class="restaurant-menu-dots"></div>
                <!-- item price -->
                <div class="restaurant-menu-price">
                  $18
                </div>
                <!-- /item price -->
              </div>
              <!-- /item title -->
              <!-- item description -->
              <div class="restaurant-menu-description">
                <p>Item description here.</p>
              </div>
              <!-- /item description -->
            </li>
            <!-- /item -->
          </ul>
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Menu - Featured Item ======== -->
      <div class="element element-menu-featured menu">
        <div class="element-wrapper">
          <!-- Item Image -->
          <div class="menu-featured-image" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/products/p2.jpg');"></div>
          <!-- /Item image -->
          <!-- Menu Element Mask -->
          <div class="menu-featured-mask-wrapper">
            <div class="menu-featured-mask">
              <!-- row -->
              <div class="row menu-featured-title-wrapper">
                <!-- item title -->
                <div class="col-xs-8">
                  <h3 class="menu-featured-title">Thata's Burguer</h3>
                </div>
                <!-- /item title -->
                <!-- item price -->
                <div class="col-xs-4">
                  <span class="menu-featured-price">$ 5.50</span>
                </div>
                <!-- /item price -->
              </div>
              <!-- /row -->
              <!-- Item Description -->
              <div class="menu-featured-description">
                <p>Type the description of the dish and show how it can be special. Make it look delicious!</p>
                <p class="menu-featured-text-small"><i><strong>Ingredients:</strong> Mushroom, Verno slices, Green onions,  Tomatoes</i></p>
              </div>
              <!-- /Item Description -->
            </div>
          </div>
          <!-- /Menu Element Mask -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Menu - Featured Item ======== -->
      <div class="element element-menu-featured menu">
        <div class="element-wrapper">
          <!-- Item Image -->
          <div class="menu-featured-image" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/products/p6.jpg');"></div>
          <!-- /Item image -->
          <!-- Menu Element Mask -->
          <div class="menu-featured-mask-wrapper">
            <div class="menu-featured-mask">
              <!-- row -->
              <div class="row menu-featured-title-wrapper">
                <!-- item title -->
                <div class="col-xs-8">
                  <h3 class="menu-featured-title">Broccoli Side</h3>
                </div>
                <!-- /item title -->
                <!-- item price -->
                <div class="col-xs-4">
                  <span class="menu-featured-price">$ 6.95</span>
                </div>
                <!-- /item price -->
              </div>
              <!-- /row -->
              <!-- Item Description -->
              <div class="menu-featured-description">
                <p>Type the description of the dish and show how it can be special. Make it look delicious!</p>
                <p class="menu-featured-text-small"><i><strong>Ingredients:</strong> Verno slices, Dolre oil, Green onions, Mushroom, Tomatoes</i></p>
              </div>
              <!-- /Item Description -->
            </div>
          </div>
          <!-- /Menu Element Mask -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Menu - Featured Item ======== -->
      <div class="element element-menu-featured menu">
        <div class="element-wrapper">
          <!-- Item Image -->
          <div class="menu-featured-image" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/products/p7.jpg');"></div>
          <!-- /Item image -->
          <!-- Menu Element Mask -->
          <div class="menu-featured-mask-wrapper">
            <div class="menu-featured-mask">
              <!-- row -->
              <div class="row menu-featured-title-wrapper">
                <!-- item title -->
                <div class="col-xs-8">
                  <h3 class="menu-featured-title">Roasted Chicken</h3>
                </div>
                <!-- /item title -->
                <!-- item price -->
                <div class="col-xs-4">
                  <span class="menu-featured-price">$ 9.95</span>
                </div>
                <!-- /item price -->
              </div>
              <!-- /row -->
              <!-- Item Description -->
              <div class="menu-featured-description">
                <p>Type the description of the dish and show how it can be special. Make it look delicious!</p>
                <p class="menu-featured-text-small"><i><strong>Ingredients:</strong> Verno slices, Dolre oil, Green onions, Mushroom, Tomatoes</i></p>
              </div>
              <!-- /Item Description -->
            </div>
          </div>
          <!-- /Menu Element Mask -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Menu - Featured Item ======== -->
      <div class="element element-menu-featured menu">
        <div class="element-wrapper">
          <!-- Item Image -->
          <div class="menu-featured-image" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/products/p3.jpg');"></div>
          <!-- /Item image -->
          <!-- Menu Element Mask -->
          <div class="menu-featured-mask-wrapper">
            <div class="menu-featured-mask">
              <!-- row -->
              <div class="row menu-featured-title-wrapper">
                <!-- item title -->
                <div class="col-xs-8">
                  <h3 class="menu-featured-title">Salmon Filet</h3>
                </div>
                <!-- /item title -->
                <!-- item price -->
                <div class="col-xs-4">
                  <span class="menu-featured-price">$ 14.95</span>
                </div>
                <!-- /item price -->
              </div>
              <!-- /row -->
              <!-- Item Description -->
              <div class="menu-featured-description">
                <p>Type the description of the dish and show how it can be special. Make it look delicious!</p>
                <p class="menu-featured-text-small"><i><strong>Ingredients:</strong> Verno slices, Dolre oil, Green onions, Mushroom, Tomatoes</i></p>
              </div>
              <!-- /Item Description -->
            </div>
          </div>
          <!-- /Menu Element Mask -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Menu - Featured Item ======== -->
      <div class="element element-menu-featured menu">
        <div class="element-wrapper">
          <!-- Item Image -->
          <div class="menu-featured-image" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/products/p5.jpg');"></div>
          <!-- /Item image -->
          <!-- Menu Element Mask -->
          <div class="menu-featured-mask-wrapper">
            <div class="menu-featured-mask">
              <!-- row -->
              <div class="row menu-featured-title-wrapper">
                <!-- item title -->
                <div class="col-xs-8">
                  <h3 class="menu-featured-title">Misto Quente</h3>
                </div>
                <!-- /item title -->
                <!-- item price -->
                <div class="col-xs-4">
                  <span class="menu-featured-price">$ 8.35</span>
                </div>
                <!-- /item price -->
              </div>
              <!-- /row -->
              <!-- Item Description -->
              <div class="menu-featured-description">
                <p>Type the description of the dish and show how it can be special. Make it look delicious!</p>
                <p class="menu-featured-text-small"><i><strong>Ingredients:</strong> Verno slices, Dolre oil, Green onions, Mushroom, Tomatoes</i></p>
              </div>
              <!-- /Item Description -->
            </div>
          </div>
          <!-- /Menu Element Mask -->
        </div>
      </div>
      <!-- ======== / Element ======== -->


      <!-- ======== Element: Service ======== -->
      <div class="element element-content element-services  home about">
        <div class="element-wrapper">
          <!-- element box -->
          <div class="element-box">
            <div class="element-box-bg">
              <!-- box title -->
              <h2 class="element-box-title"><span>Services</span></h2>
              <!-- /box-title -->
              <!-- Service Item-->
              <div class="service-item">
                <h2 class="service-title">Catering Services</h2>
                <div class="service-description">
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.</p>
                  <ul>
                    <li>Business Events</li>
                    <li>Birthdays</li>
                    <li>Weddings</li>
                    <li>Party & Others</li>
                  </ul>
                </div>
              </div>
              <!-- /Service Item -->
            </div>
          </div>
          <!-- /element-box -->
        </div>
      </div>
      <!-- ======== / Element ======== -->


      <!-- ======== Element: Team Member ======== -->
      <div class="element element-content element-team-member about">
        <div class="element-wrapper">
          <!-- Item Image -->
          <div class="menu-featured-image" style="background-image: url('http://dotrex.co/theme-preview/saveur-preview/img/team/team2.jpg');"></div>
          <!-- /Item image -->
          <!-- our staff -->
          <div class="team-member-staff">Our Staff</div>
          <!-- /our staff -->
          <!-- Menu Element Mask -->
          <div class="team-member-mask-wrapper">
            <div class="team-member-mask">
              <!-- Team Member Name -->
              <div class="team-member-text">
                <h3 class="team-member-title">Melissa Rex</h3>
                <p class="team-member-description">Cuisine Chef</p>
              </div>
              <!-- /Team Member Name -->
            </div>
          </div>
          <!-- /Menu Element Mask -->
        </div>
      </div>
      <!-- ======== / Element: Team Member ======== -->

      <!-- ======== Element: About Picture ======== -->
      <div class="element element-about-picture about">
        <div class="element-wrapper">
          <!-- link box -->
          <a href="http://dotrex.co/theme-preview/saveur-preview/img/about-picture2.jpg" class="about-picture-mask nivobox" data-lightbox-gallery="about">
            <div class="about-picture-mask-content">
              <i class="fa fa-eye"></i>
            </div>
          </a>
          <!-- /link box -->
          <!-- img -->
          <img src = "http://dotrex.co/theme-preview/saveur-preview/img/about-picture2.jpg" class="responsive-image" alt="">
          <!-- /img -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Contact Form ======== -->
      <div class="element element-big-content element-contact-form contact">
        <div class="element-wrapper">
          <p><i>Please fill the form to <strong>get in touch</strong></i></p>
          <!-- form -->
          <form id="contactForm">
            <!-- item -->
            <div class="form-group">
              <input type="text" class="form-control required" id="name" name="name" placeholder="Name*" required>
            </div>
            <!-- /item -->
            <!-- item -->
            <div class="form-group">
              <input type="email" class="form-control required" id="email" name="email" placeholder="Email" required>
            </div>
            <!-- /item -->
            <!-- item -->
            <div class="form-group">
              <input type="text" class="form-control required" id="subject" name="subject" placeholder="Subject" required>
            </div>
            <!-- /item -->
            <!-- item -->
            <div class="form-group">
              <textarea class="form-control required" name="message" id="message" placeholder="Message" required></textarea>
            </div>
            <!-- /item -->
            <!-- item -->
            <div class="form-group form-send">
              <button type="submit" value="enviar" class="btn btn-default btn-rayen" data-text="Let's Go!"><span>Submit</span></button>
            </div>
            <!-- /item -->
          </form>
          <!-- /form -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Contact Hire ======== -->
      <div class="element element-contact-map contact">
        <div class="element-wrapper">
          <!-- map -->
          <div class="map" id="map">
            <div class="map-overlay"></div>
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d8352.985568651915!2d-0.12905994797222892!3d51.50665753790812!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47d8a00baf21de75%3A0x52963a5addd52a99!2sLondon%2C+UK!5e0!3m2!1spt-BR!2sbr!4v1443128181953" style="border:0" allowfullscreen=""></iframe>
          </div>
          <!-- /map -->
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Avaliable ======== -->
      <div class="element element-contact-avaliable contact">
        <div class="element-wrapper">
          <div class="element-contact-available-mask">
            <p><span class="catering-services-title">Catering Services</span><br/><small>Complete the form to request a quote.</small></p>
          </div>
          <img src="http://dotrex.co/theme-preview/saveur-preview/img/about-picture5.jpg" class="responsive-image" alt="">
        </div>
      </div>
      <!-- ======== / Element ======== -->

      <!-- ======== Element: Contact infos ======== -->
      <div class="element element-content-3 element-contact-infos contact">
        <div class="element-wrapper">
          <div class="element-box" >
            <div class="element-box-bg">
              <!-- box title -->
              <h2 class="element-box-title"><span>Contact Infos</span></h2>
              <!-- /box-title -->
              <h3 class="contact-info-title">Phone:</h3>
              <p>+123 456 789 111</p>
              <h3 class="contact-info-title">Email:</h3>
              <p>john@dotrex.co</p>
              <h3 class="contact-info-title">Address:</h3>
              <p>451 Lorem Ipsum, <strong>London - Uk</strong></p>
            </div>
          </div>
        </div>
      </div>
      <!-- ======== / Element ======== -->


    </div>
    <!-- /Page Elements -->

  </section>
  <!-- /Main Content
  ================================================== -->


</div>
<!-- /.page container -->

<!-- Contact Form - Ajax Messages
========================================================= -->
<!-- Form Sucess -->
<div class="form-result modal-wrap" id="contactSuccess">
  <div class="modal-bg"></div>
  <div class="modal-content">
    <h4 class="modal-title"><i class="fa fa-check-circle"></i> Success!</h4>
    <p>Your message has been sent to us.</p>
  </div>
</div>
<!-- /Form Sucess -->
<!-- form-error -->
<div class="form-result modal-wrap" id="contactError">
  <div class="modal-bg"></div>
  <div class="modal-content">
    <h4 class="modal-title"><i class="fa fa-times"></i> Error</h4>
    <p>There was an error sending your message.</p>
  </div>
</div>
<!-- /form-error -->
<!-- form-sending -->
<div class="form-result modal-wrap" id="contactWait">
  <div class="modal-bg"></div>
  <div class="modal-content">
    <div class="modal-loader"></div>
    <p>Sending Message, please wait...</p>
  </div>
</div>
<!-- /form-sending -->
<!-- / Contact Form - Ajax Messages
========================================================= -->

<!-- Footer
================================================== -->
<footer id="footer">
  <div id="footer-top">
    <div class="container">
      <!-- Social icons -->
      <ul class="social-footer">
        <li><a href="#" class="btn-default btn-wapasha" data-toggle="tooltip" data-placement="top" title="Facebook"><i class="fa fa-facebook"></i></a></li>
        <li><a href="#" class="btn-default btn-wapasha" data-toggle="tooltip" data-placement="bottom" title="Twitter"><i class="fa fa-twitter"></i></a></li>
        <li><a href="#" class="btn-default btn-wapasha" data-toggle="tooltip" data-placement="top" title="Behance"><i class="fa fa-behance"></i></a></li>
        <li><a href="#" class="btn-default btn-wapasha" data-toggle="tooltip" data-placement="bottom" title="Dribbble"><i class="fa fa-dribbble"></i></a></li>
      </ul>
      <!-- /Social Icons -->
      <p class="footer-quote">"Commitment leads to action. Action brings your dream closer..."<br/><span class="footer-quote-author">Marcia Wieder</span></p>
    </div>
  </div>
  <div id="footer-bottom">
    <div class="container">
      <p class="footer-bottom-text1">451 Lorem Ipsum, London - Uk<span class="padding">·</span> (845)&nbsp;123-4567 <span class="padding">·</span> <a href="mailto:dotrex@dotrex.co" data-toggle="tooltip" data-placement="top" title="Click to mail us!">dotrex@dotrex.co</a> </p>
      <p class="footer-bottom-text2"><span>© 2016, Saveur by DotRex. All Rights Reserved.</span></p>
    </div>
  </div>
</footer>
<!-- /Footer
================================================== -->

<!-- Analytics -->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
          m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-74606252-1', 'auto');
  ga('send', 'pageview');

</script>
<!-- /Analytics -->


<!-- >> JS
============================================================================== -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src = "http://dotrex.co/theme-preview/saveur-preview/vendor/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/validate.js"></script>
<!-- Modal box-->
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/nivo-lightbox/nivo-lightbox.min.js"></script>
<!-- jQuery niceScroll -->
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/jquery.nicescroll.min.js"></script>
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/jquery.nicescroll.plus.js"></script>
<!-- jQuery CountDown -->
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/jquery.countdown.min.js"></script>
<!-- Images Loaded-->
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/imagesloaded.pkgd.min.js"></script>
<!-- Mansonry-->
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/masonry.pkgd.min.js"></script>
<!-- Isotope-->
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/jquery.ba-bbq.min.js"></script>
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/jquery.isotope2.min.js"></script>
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/packery-mode.pkgd.min.js"></script>
<!-- Waypoints -->
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/cross-browser.js"></script>
<!-- Double Tap to Go -->
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/doubletaptogo.min.js"></script>
<!-- Cross-browser -->
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/cross-browser.js"></script>
<!-- Main Scripts -->
<script src="http://dotrex.co/theme-preview/saveur-preview/js/main.js"></script>


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/html5shiv.js"></script>
<script src="http://dotrex.co/theme-preview/saveur-preview/vendor/respond.min.js"></script>
<![endif]-->
<!-- >> /JS
============================================================================= -->
</body>
</html>
