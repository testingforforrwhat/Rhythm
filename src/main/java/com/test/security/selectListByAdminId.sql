
select *
from rhythm.admin
where admin_name = 'admin';

select a.*
from
    role a
        INNER JOIN admin_role b on a.role_id = b.role_id
where
        b.admin_id =1;


-- resultMap 类的聚合关系 collection
select a.*
from
    role a
        INNER JOIN admin_role b on a.role_id = b.role_id
where
        b.admin_id =(select admin_id
                     from rhythm.admin
                     where admin_name = 'admin');