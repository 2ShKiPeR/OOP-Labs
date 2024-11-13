insert into labs.functions(id, c_function_name, c_count, c_x_from, c_x_to)
values (1, 'example_function', 10, 0, 10);

insert into labs.point(id, function_id, c_x_val, c_y_val)
values  (1, 1, 0, 0),
        (2, 1, 1, 2),
        (3, 1, 4, 8),
        (4, 1, 8, 16);