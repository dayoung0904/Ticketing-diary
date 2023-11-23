package com.ticketingdiary.show.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Show {

	private int id;
	private String name;
	private String posterImagePath;
	private String location;
	private String address;
	private Date startDate;
	private Date endDate;
	private String category;
	private String group;
	private String region;
	private String ticketType;
	private int price;
	private int limitBuy;
	private Date createdAt;
	private Date updatedAt;
}
