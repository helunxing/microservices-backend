-- insert default user
INSERT INTO public.user_table (sub, login_key)
VALUES ('github_37706103', 'default_key');
INSERT INTO public.user_table (sub, login_key)
VALUES ('undefined', 'default_key');

-- insert default event
INSERT INTO public.event_table (creator_id, title, address, date, time_options, votes_counts)
VALUES (2, 'lx''s birthday', e'Kelvin Court
30 Yorkhill Street
GXXXXX', '2023-06-07', '17:02_17:32,18:00_19:30', '0,0');
INSERT INTO public.event_table (creator_id, title, address, date, time_options, votes_counts)
VALUES (2, 'Graudate party', e'University Of Glasgow1
Gilmorehill
GXXXXX', '2023-06-17', '19:30_23:00,18:30_23:00,20:30_23:00', '0,0,0');
INSERT INTO public.event_table (creator_id, title, address, date, time_options, votes_counts)
VALUES (2, 'Meeting for class work', e'107 Kelvinhaugh Street
Flat 24
GXXXXX', '2023-06-27', '17:57_18:27,17:57_18:27,17:57_18:27', '0,0,0');
INSERT INTO public.event_table (creator_id, title, address, date, time_options, votes_counts)
VALUES (2, 'Picnic in park', e'Flat 23
107 Kelvinhaugh Street
GXXXXX', '2023-06-29', '19:30_23:00,18:30_23:00,20:30_23:00', '0,0,0');
INSERT INTO public.event_table (creator_id, title, address, date, time_options, votes_counts)
VALUES (2, 'Museum tour', e'Kelvin Court
30 Yorkhill Street
GXXXXX', '2023-08-27', '19:30_23:00,18:30_23:00,20:30_23:00', '0,0,0');
INSERT INTO public.event_table (creator_id, title, address, date, time_options, votes_counts)
VALUES (2, 'Scott and his student''s group meeting', e'Flat 22
107 Kelvinhaugh Street
GXXXXX', '2023-09-27', '19:30_23:00,18:30_23:00,20:30_23:00', '0,0,0');
INSERT INTO public.event_table (creator_id, title, address, date, time_options, votes_counts)
VALUES (1, 'Look at rental properties on the spot', e'107 Kelvinhaugh Street
Flat 24
GXXXXX', '2023-10-08', '17:02_17:32,17:03_17:33,17:03_17:33', '0,0,0');
