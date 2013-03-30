package com.redhat.errai.math.client.shared;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("math")
public interface MathService {
	
	@GET
	@Path("add")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Result add(@QueryParam("LHS") String lhs, @QueryParam("RHS") String rhs, @HeaderParam("User-Agent") String userAgent);
	
	@GET
	@Path("subtract")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Result subtract(@QueryParam("LHS") String lhs, @QueryParam("RHS") String rhs, @HeaderParam("User-Agent") String userAgent);

	
	@GET
	@Path("multiply")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Result multiply(@QueryParam("LHS") String lhs, @QueryParam("RHS") String rhs, @HeaderParam("User-Agent") String userAgent);

	
	@GET
	@Path("divide")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Result divide(@QueryParam("LHS") String lhs, @QueryParam("RHS") String rhs, @HeaderParam("User-Agent") String userAgent);
	
	@GET
	@Path("secured/exponent")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Result exponent(@QueryParam("base") String base, @QueryParam("power") String power, @HeaderParam("User-Agent") String userAgent);
	
	@GET
	@Path("secured/squareroot")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Result squareRoot(@QueryParam("num") String num, @HeaderParam("User-Agent") String userAgent);
	
	@GET
	@Path("secured/factorial")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Result factorial(@QueryParam("num") String num, @HeaderParam("User-Agent") String userAgent);



}
