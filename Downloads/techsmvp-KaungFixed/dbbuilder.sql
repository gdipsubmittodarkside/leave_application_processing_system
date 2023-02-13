drop table if exists `member`;
create table `member` (
	member_id BIGINT,
	username VARCHAR(50),
	password VARCHAR(50),
	email VARCHAR(50),
	gender VARCHAR(50),
	birthday DATE,
	dream_job_id BIGINT,
	current_city VARCHAR(50),
	education VARCHAR(50),
	short_bio VARCHAR(100),
	current_job_title VARCHAR(50),
	avatar VARCHAR(100)
	primary key (member_id)
);


insert into member (member_id, username, password, email, gender, birthday, dream_job_id, current_city, education, short_bio, current_job_title, avatar) values (1, 'fshortall0', 'nGFMCgok0', 'tscandred0@gov.uk', 'MALE', '1983-05-22', 1, 'Usukhchay', 'OTHERS', 'matrix frictionless systems', 'Tax Accountant', 'https://robohash.org/reiciendisrerumin.png?size=50x50&set=set1');
insert into member (member_id, username, password, email, gender, birthday, dream_job_id, current_city, education, short_bio, current_job_title, avatar) values (2, 'oshankland1', 'o6XfV7cOOx1', 'jthresh1@census.gov', 'MALE', '1987-11-20', 2, 'La Gi', 'MASTER', 'integrate web-enabled e-business', 'Actuary', 'https://robohash.org/laboriosamperspiciatisvitae.png?size=50x50&set=set1');
insert into member (member_id, username, password, email, gender, birthday, dream_job_id, current_city, education, short_bio, current_job_title, avatar) values (3, 'kjillins2', '7UoGHc', 'sfarrar2@alibaba.com', 'MALE', '1984-12-24', 3, 'Pingshan', 'MASTER', 'integrate revolutionary communities', 'Programmer II', 'https://robohash.org/suntutet.png?size=50x50&set=set1');
insert into member (member_id, username, password, email, gender, birthday, dream_job_id, current_city, education, short_bio, current_job_title, avatar) values (4, 'cdonlon3', '3clWpve', 'jdimanche3@wufoo.com', 'MALE', '1985-12-12', 4, 'Kuchinarai', 'DOCTORAL', 'incentivize sexy functionalities', 'Chemical Engineer', 'https://robohash.org/cumvelrepellendus.png?size=50x50&set=set1');
insert into member (member_id, username, password, email, gender, birthday, dream_job_id, current_city, education, short_bio, current_job_title, avatar) values (5, 'mpickervance4', 'bmDXZ8ry', 'ulegen4@123-reg.co.uk', 'FEMALE', '1990-12-16', 5, 'Funes', 'DIPLOMA', 'monetize B2C e-business', 'Office Assistant I', 'https://robohash.org/exercitationemutaut.png?size=50x50&set=set1');
insert into member (member_id, username, password, email, gender, birthday, dream_job_id, current_city, education, short_bio, current_job_title, avatar) values (6, 'pivanyukov5', 'NzkHJEXC', 'mschinetti5@guardian.co.uk', 'MALE', '1993-10-30', 6, 'Bilajari', 'OTHERS', 'deploy visionary action-items', 'Junior Executive', 'https://robohash.org/officiiserrorrepellendus.png?size=50x50&set=set1');
insert into member (member_id, username, password, email, gender, birthday, dream_job_id, current_city, education, short_bio, current_job_title, avatar) values (7, 'jgreenhaugh6', 'x2ExN8ht', 'wpriestley6@unc.edu', 'MALE', '1992-12-19', 7, 'El Suyatal', 'BACHELOR', 'enhance killer relationships', 'Physical Therapy Assistant', 'https://robohash.org/voluptatesaperiamfacere.png?size=50x50&set=set1');



drop table if exists schedule_event;
create table schedule_event (
	schedule_id BIGINT,
	start_date DATE,
	end_date DATE,
	my_course_id BIGINT,
	member_id BIGINT,
	primary key (schedule_id)	
);


insert into schedule_event (schedule_id, start_date, end_date, my_course_id, member_id) values (1, '2022-12-02', '2023-05-01', 1, 1);
insert into schedule_event (schedule_id, start_date, end_date, my_course_id, member_id) values (2, '2022-10-06', '2023-04-25', 2, 2);
insert into schedule_event (schedule_id, start_date, end_date, my_course_id, member_id) values (3, '2022-12-21', '2023-01-16', 3, 3);
insert into schedule_event (schedule_id, start_date, end_date, my_course_id, member_id) values (4, '2022-09-14', '2023-08-22', 4, 4);
insert into schedule_event (schedule_id, start_date, end_date, my_course_id, member_id) values (5, '2023-01-19', '2023-04-25', 5, 5);
insert into schedule_event (schedule_id, start_date, end_date, my_course_id, member_id) values (6, '2022-11-30', '2023-03-12', 6, 6);
insert into schedule_event (schedule_id, start_date, end_date, my_course_id, member_id) values (7, '2022-06-15', '2023-02-14', 7, 7);
insert into schedule_event (schedule_id, start_date, end_date, my_course_id, member_id) values (8, '2022-11-17', '2023-04-08', 8, 1);
insert into schedule_event (schedule_id, start_date, end_date, my_course_id, member_id) values (9, '2022-10-23', '2022-11-19', 9, 2);
insert into schedule_event (schedule_id, start_date, end_date, my_course_id, member_id) values (10, '2023-01-12', '2023-04-20', 10, 3);


drop table if exists my_course;
create table my_course (
	my_course_id BIGINT,
	progress BIGINT,
	skill_id BIGINT,
	my_course_title VARCHAR(150),
	schedule_id BIGINT,
	primary key (my_course_id)
);

insert into my_course (my_course_id, progress, skill_id, my_course_title, schedule_id) values (1, 12, 1, 'Spring Framework 5: Beginner to Guru',1);
insert into my_course (my_course_id, progress, skill_id, my_course_title, schedule_id) values (2, 80, 2, 'DevOps Beginners to Advanced',2);
insert into my_course (my_course_id, progress, skill_id, my_course_title, schedule_id) values (3, 64, 3, 'Master Spring Boot 3 & Spring Framework 6 with Java',3);
insert into my_course (my_course_id, progress, skill_id, my_course_title, schedule_id) values (4, 31, 4, 'Python for Data Science and Machine Learning Bootcamp',4);
insert into my_course (my_course_id, progress, skill_id, my_course_title, schedule_id) values (5, 44, 5, 'Spring Boot JPA',5);
insert into my_course (my_course_id, progress, skill_id, my_course_title, schedule_id) values (6, 26, 6, 'Spring&Hibernate for Beginner',6);
insert into my_course (my_course_id, progress, skill_id, my_course_title, schedule_id) values (7, 20, 7, 'Full stack:React and Java Spring Boot',7);
insert into my_course (my_course_id, progress, skill_id, my_course_title, schedule_id) values (8, 86, 8, 'Full Android Development Masterclass',8);
insert into my_course (my_course_id, progress, skill_id, my_course_title, schedule_id) values (9, 99, 9, 'Web Scraping in Python',9);
insert into my_course (my_course_id, progress, skill_id, my_course_title, schedule_id) values (10, 21, 10, 'C Programming For Beginners',10);


drop table if exists my_skill;
create table my_skill (
	my_skill_id BIGINT,
	skill_title VARCHAR(100),
	member_id BIGINT,
	course_taken VARCHAR(150),
	primary key (my_skill_id)
);
insert into my_skill (my_skill_id, skill_id, member_id, course_taken) values (1, 1, 1, 'Panther');
insert into my_skill (my_skill_id, skill_id, member_id, course_taken) values (2, 2, 2, 'Even Angels Eat Beans');
insert into my_skill (my_skill_id, skill_id, member_id, course_taken) values (3, 3, 3, 'Best in Show');
insert into my_skill (my_skill_id, skill_id, member_id, course_taken) values (4, 4, 4, 'I Am Trying to Break Your Heart');
insert into my_skill (my_skill_id, skill_id, member_id, course_taken) values (5, 5, 5, 'Cabin in the Woods, The');
insert into my_skill (my_skill_id, skill_id, member_id, course_taken) values (6, 6, 6, 'American Astronaut, The');
insert into my_skill (my_skill_id, skill_id, member_id, course_taken) values (7, 7, 7, 'To the Shores of Tripoli');
insert into my_skill (my_skill_id, skill_id, member_id, course_taken) values (8, 8, 1, 'Capturing the Friedmans');
insert into my_skill (my_skill_id, skill_id, member_id, course_taken) values (9, 9, 2, 'Kill Me Again');
insert into my_skill (my_skill_id, skill_id, member_id, course_taken) values (10,10, 3, 'Lunch Break');


drop table if exists job;
create table job (
	job_id BIGINT,
	job_title VARCHAR(100),
	primary key (job_id)
);

insert into job (job_id, job_title) values (1, 'Teacher');
insert into job (job_id, job_title) values (2, 'VP Quality Control');
insert into job (job_id, job_title) values (3, 'Teacher');
insert into job (job_id, job_title) values (4, 'Electrical Engineer');
insert into job (job_id, job_title) values (5, 'Assistant Professor');
insert into job (job_id, job_title) values (6, 'Staff Scientist');
insert into job (job_id, job_title) values (7, 'Help Desk Operator');
insert into job (job_id, job_title) values (8, 'Financial Advisor');
insert into job (job_id, job_title) values (9, 'Cost Accountant');
insert into job (job_id, job_title) values (10, 'Help Desk Technician');
insert into job (job_id, job_title) values (11, 'Financial Analyst');
insert into job (job_id, job_title) values (12, 'Food Chemist');
insert into job (job_id, job_title) values (13, 'Internal Auditor');
insert into job (job_id, job_title) values (14, 'Analog Circuit Design manager');
insert into job (job_id, job_title) values (15, 'Occupational Therapist');
insert into job (job_id, job_title) values (16, 'Safety Technician IV');
insert into job (job_id, job_title) values (17, 'Business Systems Development Analyst');
insert into job (job_id, job_title) values (18, 'Paralegal');
insert into job (job_id, job_title) values (19, 'Software Consultant');
insert into job (job_id, job_title) values (20, 'Technical Writer');

drop table if exists skill;
create table skill (
	skill_id BIGINT,
	url_link VARCHAR(2000),
	skill_title VARCHAR(100),
	primary key (skill_id)

);

insert into skill (skill_id, url_link, skill_title) values (1, 'http://hexun.com/rhoncus/aliquet/pulvinar/sed/nisl/nunc/rhoncus.html', 'C sharp');
insert into skill (skill_id, url_link, skill_title) values (2, 'https://exblog.jp/et.js', 'Java');
insert into skill (skill_id, url_link, skill_title) values (3, 'http://usa.gov/vitae.jsp', 'Java Web');
insert into skill (skill_id, url_link, skill_title) values (4, 'http://vinaora.com/consequat.aspx', 'Spring Boot');
insert into skill (skill_id, url_link, skill_title) values (5, 'https://usa.gov/eros/suspendisse/accumsan.jpg', 'Spring 5 Framwork');
insert into skill (skill_id, url_link, skill_title) values (6, 'https://bluehost.com/purus/sit/amet/nulla/quisque.jpg', 'Spring MVC');
insert into skill (skill_id, url_link, skill_title) values (7, 'http://nifty.com/ac/tellus/semper/interdum/mauris/ullamcorper.js', 'Python');
insert into skill (skill_id, url_link, skill_title) values (8, 'https://usnews.com/nunc/nisl/duis/bibendum/felis/sed/interdum.html', 'Web Crawling');
insert into skill (skill_id, url_link, skill_title) values (9, 'http://sciencedirect.com/lacus/curabitur/at.jpg', 'HTML');
insert into skill (skill_id, url_link, skill_title) values (10, 'http://phoca.cz/quam.png', 'CSS');
insert into skill (skill_id, url_link, skill_title) values (11, 'http://is.gd/aliquam.json', 'Javascript');
insert into skill (skill_id, url_link, skill_title) values (12, 'https://naver.com/et/magnis/dis.png', 'Relational Database');
insert into skill (skill_id, url_link, skill_title) values (13, 'http://wsj.com/quisque/arcu/libero/rutrum.jsp', 'Back End');
insert into skill (skill_id, url_link, skill_title) values (14, 'http://admin.ch/tellus/semper/interdum.aspx', 'Quality Assurance');
insert into skill (skill_id, url_link, skill_title) values (15, 'https://shop-pro.jp/sociis/natoque.json', 'Data Analysis');
insert into skill (skill_id, url_link, skill_title) values (16, 'https://qq.com/elementum/in/hac/habitasse.json', 'Frond End');
insert into skill (skill_id, url_link, skill_title) values (17, 'http://blogspot.com/nonummy/maecenas/tincidunt/lacus.jpg', 'Data Virtualization');
insert into skill (skill_id, url_link, skill_title) values (18, 'https://networkadvertising.org/quis/orci.xml', 'Machine Learning');
insert into skill (skill_id, url_link, skill_title) values (19, 'https://nbcnews.com/sed.jpg', 'Responsive Web Design');
insert into skill (skill_id, url_link, skill_title) values (20, 'http://telegraph.co.uk/sed/lacus/morbi/sem/mauris.json', 'Ruby');

drop table if exists job_skill;
create table job_skill (
	job_skill_id BIGINT,
	job_id BIGINT,
	skill_id BIGINT,
	last_updated_date DATE, 
	primary key(job_skill_id)
);

insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (1, 1, 1, '2022-04-10');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (2, 2, 2, '2022-12-11');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (3, 3, 3, '2022-06-01');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (4, 4, 4, '2022-03-07');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (5, 5, 5, '2022-09-24');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (6, 6, 6, '2022-10-06');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (7, 7, 7, '2022-08-17');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (8, 8, 8, '2022-08-10');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (9, 9, 9, '2022-10-24');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (10, 10, 10, '2022-11-25');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (11, 11, 11, '2022-07-03');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (12, 12, 12, '2022-11-27');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (13, 13, 13, '2022-06-05');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (14, 14, 14, '2022-12-08');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (15, 15, 15, '2022-06-27');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (16, 16, 16, '2022-04-20');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (17, 17, 17, '2022-09-05');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (18, 18, 18, '2022-03-26');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (19, 19, 19, '2022-08-16');
insert into job_skill (job_skill_id, job_id, skill_id, last_updated_date) values (20, 20, 20, '2022-02-23');

drop table if exists course_crawled;
create table course_crawled (
	course_id BIGINT,
	course_title VARCHAR(150),
	url_link VARCHAR(2000),
	views BIGINT,
	likes BIGINT,
	subscribers BIGINT,
	skill_id BIGINT,
	Primary Key (course_id)
);

insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (1, 'Telugu', 'https://google.cn/vivamus/vestibulum/sagittis/sapien/cum.js', 1, 1, 1, 1);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (2, 'French', 'http://mac.com/imperdiet/sapien/urna.xml', 2, 2, 2, 2);
insert into course_crawled (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (3, 'Tetum', 'https://smh.com.au/laoreet/ut/rhoncus/aliquet/pulvinar/sed/nisl.jpg', 3, 3, 3, 3);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (4, 'Kyrgyz', 'https://symantec.com/nisl/nunc.json', 4, 4, 4, 4);
insert into course_crawled (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (5, 'Swati', 'http://boston.com/leo/odio/condimentum/id/luctus.js', 5, 5, 5, 5);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (6, 'Lao', 'https://nps.gov/est/quam/pharetra/magna/ac.xml', 6, 6, 6, 6);
insert into course_crawled (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (7, 'Irish Gaelic', 'https://phoca.cz/congue/vivamus/metus/arcu/adipiscing/molestie/hendrerit.html', 7, 7, 7, 7);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (8, 'Estonian', 'https://squidoo.com/cras/non/velit.js', 8, 8, 8, 8);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (9, 'Nepali', 'https://bbc.co.uk/mi/pede/malesuada.js', 9, 9, 9, 9);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (10, 'Aymara', 'https://berkeley.edu/iaculis.aspx', 10, 10, 10, 10);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (11, 'Dari', 'https://woothemes.com/magna/vestibulum/aliquet/ultrices.json', 11, 11, 11, 11);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (12, 'Hindi', 'https://howstuffworks.com/augue/vestibulum.xml', 12, 12, 12, 12);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (13, 'Latvian', 'https://cyberchimps.com/vel/lectus/in.aspx', 13, 13, 13, 13);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (14, 'Swahili', 'https://spotify.com/eleifend/quam/a.jsp', 14, 14, 14, 14);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (15, 'Moldovan', 'https://independent.co.uk/tortor/quis/turpis.jsp', 15, 15, 15, 15);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (16, 'Pashto', 'https://dailymail.co.uk/morbi/non/lectus/aliquam/sit/amet/diam.jpg', 16, 16, 16, 16);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (17, 'Romanian', 'http://thetimes.co.uk/orci/luctus.html', 17, 17, 17, 17);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (18, 'Oriya', 'https://tinypic.com/ut/mauris.aspx', 18, 18, 18, 18);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (19, 'Spanish', 'http://nsw.gov.au/nisi/venenatis/tristique/fusce/congue.jpg', 19, 19, 19, 19);
insert into course_crawled  (course_id, course_title, url_link, views, likes, subscribers, skill_id) values (20, 'Luxembourgish', 'http://berkeley.edu/curae/donec/pharetra.aspx', 20, 20, 20, 20);


alter table `member` add foreign key (dream_job_id) references job (job_id);
alter table schedule_event add foreign key (my_course_id) references my_course (my_course_id);
alter table schedule_event add foreign key (member_id) references `member` (member_id);
alter table my_course add foreign key (schedule_id) references schedule_event (schedule_id);
alter table my_course add foreign key (skill_id) references skill (skill_id);
alter table my_skill add foreign key (member_id) references `member` (member_id);
alter table job_skill add foreign key (skill_id) references skill (skill_id);
alter table job_skill add foreign key (job_id) references job (job_id);
alter table course_crawled add foreign key (skill_id) references skill (skill_id);

