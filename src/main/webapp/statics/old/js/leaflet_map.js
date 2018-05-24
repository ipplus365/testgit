window.mapCreated = false;
	window.parent.createMap = function(){
		if(window.mapCreated == false){
			window.mapCreated = true;
			window.map = new window.L.Map('map', {
				zoomControl:false
			});
			window.markers = new Array();
			window.circles = new Array();
			window.LEDfeature = new Array();
			window.cloudmadeUrl = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',cloudmadeAttrid = 'Map data &copy; 2011 OpenStreetMap contributors, Imagery &copy; 2011 CloudMade';
			window.cloudmade = new window.L.tileLayer(cloudmadeUrl,{
				maxZoom:18,
//				attribution:cloudmadeAttrid
			});
			window.map.setView(new window.L.LatLng(39.915168, 116.403875), 5).addLayer(cloudmade);
			window.map.addControl(L.Control.Zoom({
				position:'topleft'
			}));
		}
	}
	window.parent.resetMap = function(){
		if(window.mapCreated){
			for(var i = 0; i < window.circles.length;i++){
				window.map.removeLayer(circles[i]);
			}
			window.circles = new Array();
			for(var i = 0; i < window.markers.length; i++){
				window.map.removeLayer(markers[i]);
			}
			window.markers = new Array();
			if(window.LEDfeature[0] == 1 ||　window.LEDfeature[1] == 1){
				R1LEDfeature.remove();
			}
			window.LEDfeature = new Array();
			window.LEDfeature[0] = 0;
			window.LEDfeature[1] = 0;
			window.map.setView(L.LatLng(39.915168, 116.403875), 3);
		}
	}
	window.parent.resetMarker = function(){
		if(window.mapCreated){
			for(var i = 0; i < window.markers.length; i++){
				window.map.removeLayer(window.markers[i]);
			}
			window.markers = new Array();
		}
	}
	window.parent.setViewFitToCircle = function(circleIndex){
		if(window.mapCreated){
			window.map.fitBounds(window.circle[circleIndex].getBounds());
		}
	}
	window.parent.setViewFitToMarker = function(markerIndex){
		if(window.mapCreated){
			window.map.fitBounds(window.markers[markerIndex].getBounds());
		}
	}
	window.parent.setViewFitToPolygon = function(polygonIndex){
		if(window.mapCreated){
			window.map.fitBounds(window.polygon[polygonIndex].getBounds());
		}
	}
	window.parent.setCircleRed = function(x, y, radius, i){
		if(window.mapCreated){
			window.circleLocation = new window.L.LatLng(x, y);
			window.circleOptions = {color:'#F03'};
			window.circle = new window.L.circle(window.circleLocation,radius,window.circleOption);
			window.map.addLayer(circle);
			window.circles[i] = circle;
		}
	}
	window.parent.setCenter = function(x, y, i){
		if(window.mapCreated){
			window.map.setView(new window.L.LatLng(x, y), i);
		}
	}
	window.parent.setMapMaker = function(x, y, i, j){
		if(window.mapCreated){
			window.markerLocation = new window.L.LatLng(x, y),
			options={title:i},
			marker = new window.L.LatLng(window.markerLocation,options);
			window.map.addLayer(marker);
			window.markers[1] = marker;
		}
	}
	window.parent.setCircleYellow = function(x, y, fullname, marker_number){
		if(window.mapCreated){
			window.markerLocation = new window.L.LatLng(x, y), options={},marker = new window.L.Marker(window.markerLocation,options);
			marker.bindPopup(fullname).openPopup();
			window.map.addLayer(marker);
			window.markers[marker_number] = marker;
		}
	}
	window.parent.setCircleBlue = function(x, y, radius, i){
		if(window.mapCreated){
			window.circleLocation = new window.L.LatLng(x, y),
			circleOptions = {
				color:'#f03'
			},
			circle = new window.L.Circle(window.circleLocation,radius,circleOptions);
			window.map.addLayer(circle);
			window.circles[i] = circle;
		}
	}
	  function setPoints(x,y,led,x1,y1,r1,r2){
			var LEDLatLng =L.LatLng(x,y);
			var R1R2LatLng = L.LatLng(x1,y1);
			distance = map.distance(R1R2LatLng,LEDLatLng);
			window.LEDcircleLocation = L.LatLng(x,y),
			LEDcircleOptions = {
				color:'#636363',
				fillColor:'#BFBFFF',
				fillOpacity:0.5
			},
			LEDcircle = L.Circle(window.LEDcircleLocation,led,LEDcircleOptions);
			window.map.addLayer(LEDcircle);
//			window.circles[i] = LEDcircle;
//			i++;
//			window.parent.setViewFitToCircle(0);
			if(r1 + led > distance && Math.abs(r1 - led)){
				window.R1circleLocation = L.LatLng(x1,y1),
				R1circleOptions = {
					fill:false,
					color:'rgba(0,0,0,0)',
					fillColor:'reg',
					fillOpacity:0.6
				},
//				window.LEDfeature[0] = 1;
				R1circle = L.Circle(window.R1circleLocation,r1,window.R1circleOptions);
				map.addLayer(R1circle);
//				window.circle[i] = R1circle;
//				i++;
			}else{
				window.R1circleLocation = new window.L.LatLng(x1,y1),
				R1circleOptions = {
					color:'red',
					fillColor:'#f03',
					fillOpacity:0.5
				},
				R1circle = L.LatLng(window.R1circleLocation,r1,window.R1circleOptions);
				map.addLayer(R1circle);
//				window.circles[i] = R1circle;
//				i++;
			}
			if(r2 + led > distance && Math.abs(r2 - led) < distance){
				window.R2circleLocation = L.LatLng(x1,y1),
				R2circleOptions = {
					color:'rgba(0,0,0,0)',
					fill:false,
					fillColor:'blue',
					fillOpacity:0.6
				},
				R2circle = new window.L.Circle(window.R2circleLocation,r2,window.R2circleOptions);
				map.addLayer(R2circle);
//				window.LEDfeature[1] = 1;
//				window.circle[i] = R2circle;
//				i++;
			}else{
				window.R2circleLocation = L.LatLng(x1, y1),
				R2circleOptions = {
					color:'#3A5FCD',
					fillColor:'#63B8FF',
					fillOpacity:0.5
				},
				R2circle = new window.L.Circle(window.R2circleLocation,r2,R2circleOptions);
				window.map.addLayer(R2circle);
//				window.circles[i] = R2circle;
//				i++;
			}
			R1LEDfeature = draw_intersect(R1circle,LEDcircle,'red','#f03',0.5,map);
		
	}
	window.parent.isMapCreated = function(){
		if(window.mapCreated){
			return true;
		}
		return false;
	}
	function loadMap(){
		if(window.mapCreated){
			window.parent.resetMap();
		}else{
			window.parent.createMap();
		}
	}