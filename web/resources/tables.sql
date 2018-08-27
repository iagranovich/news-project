/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  user129
 * Created: Aug 26, 2018
 */

DROP TABLE IF EXISTS items;


CREATE TABLE IF NOT EXISTS articles (
  id INT(11) NOT NULL AUTO_INCREMENT,
  date DATE,
  author VARCHAR(50),
  title VARCHAR(250),  
  description VARCHAR(5000),
  url VARCHAR (250) NOT NULL,
  image VARCHAR (1000),
  category VARCHAR(50) NOT NULL,
  
  PRIMARY KEY (id),
  UNIQUE KEY art_id_uindex (id)    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO articles (author, title, description, url, image, category) 
VALUES(
    "Jacob Kleinman", 
    "Use Slack to Schedule Meetings With This Service",
    "The only thing worse than going to meetings is scheduling them. You have to check with everyone’s availability, find a time and location that works,",
    "https://lifehacker.com/use-slack-to-schedule-meetings-with-this-service-1822979744",
    "https://i.kinja-img.com/gawker-media/image/upload/s-QqdAh63x-/c_fill,fl_progressive,g_center,h_450,q_80,w_800/qrt1sjs0ddzb7ak0sbap.png",
    "blockchain");
