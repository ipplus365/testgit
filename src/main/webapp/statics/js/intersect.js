function update(feature,circle1,circle2) {
	circle1._projectLatlngs();
	circle2._projectLatlngs();
	//circle1.projectLatlngs_2(map);
	//circle2.projectLatlngs_2(map);

	var x1=circle1._point.x,y1=circle1._point.y,r1= circle1._radius;
	var x2=circle2._point.x,y2=circle2._point.y,r= circle2._radius;
	var interPoints = intersection(x1, y1, r1, x2, y2, r);

	feature.attr("d", function() {
		return "M" + interPoints[0] + "," + interPoints[2] + "A" + r+ "," + r+
			" 0 0,1 " + interPoints[1] + "," + interPoints[3]+ "A" + r1+ "," + r1 +
			" 0 1,1 " + interPoints[0] + "," + interPoints[2];
	})

}

function R1update(R1feature,circle1,circle2){
//	circle1.projectLatlngs();
//	circle2.projectLatlngs();
	//circle1.projectLatlngs_2(map);
	//circle2.projectLatlngs_2(map);

	var x1=circle1._point.x,y1=circle1._point.y,r1= circle1._radius;
	var x2=circle2._point.x,y2=circle2._point.y,r= circle2._radius;
	var interPoints = intersection(x1, y1, r1, x2, y2, r);
	
//	console.log(interPoints)
//	console.log("x1--------"+x1);
//	console.log("y1--------"+y1);
//	console.log("r1--------"+r1);
//	console.log("x2--------"+x2);
//	console.log("y2--------"+y2);
//	console.log("r--------"+r);
	R1feature.attr("d", function() {
		return "M" + interPoints[0] + "," + interPoints[2] + "A" + r+ "," + r+
			" 0 0,1 " + interPoints[1] + "," + interPoints[3]+ "A" + r1+ "," + r1 +
			" 0 1,1 " + interPoints[0] + "," + interPoints[2];
	})

}
function R2update(R2feature,circle1,circle2) {
//	circle1.projectLatlngs();
//	circle2.projectLatlngs();
	//circle1.projectLatlngs_2(map);
	//circle2.projectLatlngs_2(map);

	var x1=circle1._point.x,y1=circle1._point.y,r1= circle1._radius;
	var x2=circle2._point.x,y2=circle2._point.y,r= circle2._radius;
	var interPoints = intersection(x1, y1, r1, x2, y2, r);
	
//	console.log("x1--------"+x1);
//	console.log("y1--------"+y1);
//	console.log("r1--------"+r1);
//	console.log("x2--------"+x2);
//	console.log("y2--------"+y2);
//	console.log("r--------"+r);

	R2feature.attr("d", function(){
		return "M" + interPoints[0] + "," + interPoints[2] + "A" + r + "," + r +
			" 0 0,1 " + interPoints[1] + "," + interPoints[3] + "A" + r1 + "," + r1 +
			" 0 1,1 " + interPoints[0] + "," + interPoints[2];
	})

}

function intersection(x0, y0, r0, x1, y1, r1){
	var a, dx, dy, d, h, rx, ry;
	var x2, y2;

	/* dx and dy are the vertical and horizontal distances between
	 * the circle centers.
	 */
	dx = x1 - x0;
	dy = y1 - y0;

	/* Determine the straight-line distance between the centers. */
	d = Math.sqrt((dy * dy) + (dx * dx));

	/* Check for solvability. */
	if (d > (r0 + r1)){
		/* no solution. circles do not intersect. */
		return false;
	}
	if (d < Math.abs(r0 - r1)){
		/* no solution. one circle is contained in the other */
		return false;
	}

	/* 'point 2' is the point where the line through the circle
	 * intersection points crosses the line between the circle
	 * centers.
	 */

	/* Determine the distance from point 0 to point 2. */
	a = ((r0 * r0) - (r1 * r1) + (d * d)) / (2.0 * d);

	/* Determine the coordinates of point 2. */
	x2 = x0 + (dx * a / d);
	y2 = y0 + (dy * a / d);

	/* Determine the distance from point 2 to either of the
	 * intersection points.
	 */
	h = Math.sqrt((r0 * r0) - (a * a));

	/* Now determine the offsets of the intersection points from
	 * point 2.
	 */
	rx = -dy * (h / d);
	ry = dx * (h / d);

	/* Determine the absolute intersection points. */
	var xi = x2 + rx;
	var xi_prime = x2 - rx;
	var yi = y2 + ry;
	var yi_prime = y2 - ry;
	var large_arc_flag=0;

	max_r=Math.max(r0,r1);
	if (a<max_r){
		large_arc_flag=1;
	}
	return [xi, xi_prime, yi, yi_prime,large_arc_flag];
}

function draw_intersect(circle1,circle2,color,fillColor,fillOpacity,mymap){
	var map = mymap;
//	map._initPathRoot();

	/* We simply pick up the SVG from the map object */
	var svg = d3.select("#map").select("svg"),
		g = svg.append("g").attr("id", "tt");
	
	/*var svg = d3.select("#map").select("svg"),
		g1 = svg.append("g").attr("id1", "tt");*/

	mycircle={"objects":[
		{"circle":{
			//"coordinates":[-41.28,174.77]
		},
			
		},

	]};
	
	
	if(window.LEDfeature[0] == 1 ){
		R1feature = g.selectAll("path1")
		.data(mycircle.objects)
		.enter().append("path")
		.attr("id", "the_SVG_ID")
		.style("stroke", '#7b7bd5')
		.style("stroke-width", 3)
		.style("opacity", 0.47)
		.style("fill", '#fff')
	;
		R1update(R1feature,R1circle,LEDcircle);
	}
	
	if(window.LEDfeature[1] == 1 ){
		//g1 = svg.append("g").attr("id1", "tt");
		R2feature = g.selectAll("path1")
		.data(mycircle.objects)
		.enter().append("path")
		.attr("id", "the_SVG_ID1")
		.style("stroke", '#7efdf2')
		.style("stroke-width", 3)
		.style("opacity", 0.34)
		.style("fill", '#0039fe')
	;
		R2update(R2feature,R2circle,LEDcircle);

	}
	
	map.on('zoomend', function(e) {
		
		if(window.LEDfeature[0] == 1){
			R1update(R1feature,R1circle,LEDcircle);
		}
		if(window.LEDfeature[1] == 1){
			
			R2update(R2feature,R2circle,LEDcircle);
		}
	});
	//update(feature,circle1,circle2);
	return g;

}