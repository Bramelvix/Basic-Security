<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Crypto</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
	integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
	integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>
<!-- Own style -->
<link rel="stylesheet" type="text/css" href="styles/global.css">
<link rel="stylesheet" type="text/css" href="styles/crypto.css">
</head>

<body class="middle-blue">

	<header> <a href="#menu-toggle"
		class="btn btn-default hidden-md-up" id="menu-toggle"> <span></span>
		<span></span> <span></span>
	</a></header>

	<div id="wrapper" class="toggled">
		<!-- Sidebar -->
		<div id="sidebar-wrapper" class="dark-blue">
			<ul class="sidebar-nav">
				<li><a href="index.jsp"><img
						src="images/lock.svg" alt="Encrypt message" title="Encrypt message" />
						Encrypt message
				</a></li>
				<li><a href="decrypt.jsp"><img
						src="images/key.svg" alt="Messages" title="Decrypt messages" /> 
						Decrypt message</a>
				</li>
			</ul>
		</div>
		<!-- /#sidebar-wrapper -->
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<img id="logo" src="images/LogoWit.svg" alt="Crypto logo" /> 
		
				<div class="row">
					<div class="col-lg-12">
						<h1>Message succesfully encrypted and saved in file <%= request.getAttribute("outputFileName") %></h1>
					</div>
				</div>


			</div>
			<!-- /#page-content-wrapper -->

		</div>
		<!-- /#wrapper -->

		<!-- jQuery -->
		<script src="vendor/jquery-1.11.2.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="vendor/bootstrap.min.js"></script>

		<!-- Menu Toggle Script -->
		<script>
			$("#menu-toggle").click(function(e) {
				e.preventDefault();
				$("#wrapper").toggleClass("toggled");
			});
		</script>
		<!-- Latest compiled and minified JavaScript -->
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
			crossorigin="anonymous"></script>
</body>
</html>
