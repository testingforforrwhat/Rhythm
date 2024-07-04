

-- operate
-- permission_operate
-- role_permission
-- role

--  1: 音乐模块 音乐管理模块
--  2: 音乐模块

SELECT
    d.*
FROM
    `operate` a
        INNER JOIN `permission_operate` b on a.operate_id = b.operate_id
        INNER JOIN `role_permission` c on b.permission_id = c.permission_id
        INNER JOIN `role` d on c.role_id = d.role_id
WHERE
        a.operate_url = '/api/categories/add'


SELECT
    d.*
FROM
    `operate` a
        INNER JOIN `permission_operate` b on a.operate_id = b.operate_id
        INNER JOIN `role_permission` c on b.permission_id = c.permission_id
        INNER JOIN `role` d on c.role_id = d.role_id
WHERE
        a.operate_url = '/api/music/add'