-- 创建需要的视图

DROP VIEW IF EXISTS port_dept;
CREATE VIEW port_dept AS SELECT
 `d`.`dept_id` AS `No`,
 `d`.`dept_name` AS `Name`,
 '' AS `NameOfPath`,
 `d`.`parent_id` AS `ParentNo`,
 '' AS `Idx`,
 '' AS `OrgNo`,
 `d`.`leader` AS `Leader`,
 '' AS `DeptType`
 FROM	`sys_dept` `d` ;


DROP VIEW IF EXISTS port_deptemp;
CREATE VIEW port_deptemp AS SELECT
concat( `sd`.`dept_id`, '_', `uu`.`user_name` ) AS `MyPK`,
`sd`.`dept_id` AS `FK_Dept`,
`uu`.`user_name` AS `FK_Emp`,
'' AS `OrgNo`
 FROM
 ( `sys_user` `uu` JOIN `sys_dept` `sd` )
 WHERE ( `uu`.`dept_id` = `sd`.`dept_id` );

DROP VIEW IF EXISTS port_deptempstation;
CREATE VIEW port_deptempstation AS SELECT
   concat( `sd`.`dept_id`, '_', `uu`.`user_name`, '_', `sp`.`post_id` ) AS `MyPK`,
   `sd`.`dept_id` AS `FK_Dept`,
   `sp`.`post_id` AS `FK_Station`,
   `uu`.`user_name` AS `FK_Emp`,
   '' AS `OrgNo`
FROM
   (( `sys_user` `uu` JOIN `sys_dept` `sd` ) JOIN `sys_user_post` `sp` )
WHERE
   (( `uu`.`user_id` = `sp`.`user_id`  )  AND ( `uu`.`dept_id` = `sd`.`dept_id` ))	;


DROP VIEW IF EXISTS port_deptstation;
CREATE VIEW port_deptstation AS SELECT
    `sd`.`dept_id` AS `FK_Dept`,
    `sp`.`post_id` AS `FK_Station`
FROM
    (( `sys_user` `uu` JOIN `sys_dept` `sd` ) JOIN `sys_user_post` `sp` )
WHERE
    ((  `uu`.`user_id` = `sp`.`user_id` )  AND ( `uu`.`dept_id` = `sd`.`dept_id` ));

DROP VIEW IF EXISTS port_emp;
CREATE VIEW port_emp AS SELECT
    `u`.`user_name` AS `No`,
    `u`.`nick_name` AS `Name`,
    `u`.`password` AS `Pass`,
    concat( `u`.`dept_id`, '' ) AS `FK_Dept`,
    '' AS `SID`,
    `u`.`phonenumber` AS `Tel`,
    `u`.`email` AS `Email`,
    '' AS `PinYin`,
    '' AS `SignType`,
    '' AS `Idx`,
    '' AS `UserType`,
    '' AS `OrgNo`,
    '' AS `Token`,
    '' AS `Leader`,
    '' AS `LeaderName`,
    '0' AS `EmpSta`
FROM
    `sys_user` `u`;

DROP VIEW IF EXISTS port_station;
CREATE VIEW port_station AS SELECT
concat( `p`.`post_id`, '' ) AS `No`,
`p`.`post_name` AS `Name`,
'1' AS `FK_StationType`,
'' AS `OrgNo`,
`p`.`post_sort` AS `idx`
 FROM
`sys_post` `p`;


DROP VIEW IF EXISTS v_flowstarterbpm;
CREATE VIEW v_flowstarterbpm AS SELECT
    `a`.`FK_Flow` AS `FK_Flow`,
    `a`.`FlowName` AS `FlowName`,
    `c`.`FK_Emp` AS `FK_Emp`,
    `c`.`OrgNo` AS `OrgNo`
FROM
    (( `wf_node` `a` JOIN `wf_nodestation` `b` ) JOIN `port_deptempstation` `c` )
WHERE
    ((
             `a`.`NodePosType` = 0
         )
        AND ((
                     `a`.`WhoExeIt` = 0
                 )
            OR ( `a`.`WhoExeIt` = 2 ))
        AND ( `a`.`NodeID` = `b`.`FK_Node` )
        AND ( `b`.`FK_Station` = `c`.`FK_Station` )
        AND ((
                     `a`.`DeliveryWay` = 0
                 )
            OR ( `a`.`DeliveryWay` = 14 ))) UNION
SELECT
    `a`.`FK_Flow` AS `FK_Flow`,
    `a`.`FlowName` AS `FlowName`,
    `c`.`FK_Emp` AS `FK_Emp`,
    `c`.`OrgNo` AS `OrgNo`
FROM
    (( `wf_node` `a` JOIN `wf_nodedept` `b` ) JOIN `port_deptemp` `c` )
WHERE
    ((
             `a`.`NodePosType` = 0
         )
        AND ((
                     `a`.`WhoExeIt` = 0
                 )
            OR ( `a`.`WhoExeIt` = 2 ))
        AND ( `a`.`NodeID` = `b`.`FK_Node` )
        AND ( `b`.`FK_Dept` = `c`.`FK_Dept` )
        AND ( `a`.`DeliveryWay` = 1 )) UNION
SELECT
    `a`.`FK_Flow` AS `FK_Flow`,
    `a`.`FlowName` AS `FlowName`,
    `b`.`FK_Emp` AS `FK_Emp`,
    '' AS `OrgNo`
FROM
    ( `wf_node` `a` JOIN `wf_nodeemp` `b` )
WHERE
    ((
             `a`.`NodePosType` = 0
         )
        AND ((
                     `a`.`WhoExeIt` = 0
                 )
            OR ( `a`.`WhoExeIt` = 2 ))
        AND ( `a`.`NodeID` = `b`.`FK_Node` )
        AND ( `a`.`DeliveryWay` = 3 )) UNION
SELECT
    `a`.`FK_Flow` AS `FK_Flow`,
    `a`.`FlowName` AS `FlowName`,
    `b`.`NO` AS `FK_Emp`,
    `b`.`OrgNo` AS `OrgNo`
FROM
    ( `wf_node` `a` JOIN `port_emp` `b` )
WHERE
    ((
             `a`.`NodePosType` = 0
         )
        AND ((
                     `a`.`WhoExeIt` = 0
                 )
            OR ( `a`.`WhoExeIt` = 2 ))
        AND ( `a`.`DeliveryWay` = 4 )) UNION
SELECT
    `a`.`FK_Flow` AS `FK_Flow`,
    `a`.`FlowName` AS `FlowName`,
    `e`.`FK_Emp` AS `FK_Emp`,
    `e`.`OrgNo` AS `OrgNo`
FROM
    ((( `wf_node` `a` JOIN `wf_nodedept` `b` ) JOIN `wf_nodestation` `c` ) JOIN `port_deptempstation` `e` )
WHERE
    ((
             `a`.`NodePosType` = 0
         )
        AND ((
                     `a`.`WhoExeIt` = 0
                 )
            OR ( `a`.`WhoExeIt` = 2 ))
        AND ( `a`.`NodeID` = `b`.`FK_Node` )
        AND ( `a`.`NodeID` = `c`.`FK_Node` )
        AND ( `b`.`FK_Dept` = `e`.`FK_Dept` )
        AND ( `c`.`FK_Station` = `e`.`FK_Station` )
        AND ( `a`.`DeliveryWay` = 9 )) UNION
SELECT
    `a`.`FK_Flow` AS `FK_Flow`,
    `a`.`FlowName` AS `FlowName`,
    `c`.`NO` AS `FK_Emp`,
    `b`.`OrgNo` AS `OrgNo`
FROM
    (( `wf_node` `a` JOIN `wf_floworg` `b` ) JOIN `port_emp` `c` )
WHERE
    ((
             `a`.`FK_Flow` = `b`.`FlowNo`
         )
        AND (
             `b`.`OrgNo` = CONVERT ( `c`.`OrgNo` USING utf8 ))
        AND ( `a`.`DeliveryWay` = 22 ));

DROP VIEW IF EXISTS wf_empworks;
CREATE VIEW wf_empworks AS SELECT
   `a`.`PRI` AS `PRI`,
   `a`.`WorkID` AS `WorkID`,
   `b`.`IsRead` AS `IsRead`,
   `a`.`Starter` AS `Starter`,
   `a`.`StarterName` AS `StarterName`,
   `a`.`WFState` AS `WFState`,
   `a`.`FK_Dept` AS `FK_Dept`,
   `a`.`DeptName` AS `DeptName`,
   `b`.`FK_Dept` AS `TodoEmpDeptNo`,
   `a`.`FK_Flow` AS `FK_Flow`,
   `a`.`FlowName` AS `FlowName`,
   `a`.`PWorkID` AS `PWorkID`,
   `a`.`PFlowNo` AS `PFlowNo`,
   `b`.`FK_Node` AS `FK_Node`,
   `b`.`FK_NodeText` AS `NodeName`,
   `a`.`Title` AS `Title`,
   `a`.`RDT` AS `RDT`,
   `b`.`RDT` AS `ADT`,
   `b`.`SDT` AS `SDT`,
   `b`.`FK_Emp` AS `FK_Emp`,
   `b`.`FID` AS `FID`,
   `a`.`FK_FlowSort` AS `FK_FlowSort`,
   `a`.`SysType` AS `SysType`,
   `a`.`SDTOfNode` AS `SDTOfNode`,
   `b`.`PressTimes` AS `PressTimes`,
   `a`.`GuestNo` AS `GuestNo`,
   `a`.`GuestName` AS `GuestName`,
   `a`.`BillNo` AS `BillNo`,
   `a`.`TodoEmps` AS `TodoEmps`,
   `a`.`TodoEmpsNum` AS `TodoEmpsNum`,
   `a`.`TodoSta` AS `TodoSta`,
   `a`.`TaskSta` AS `TaskSta`,
   `a`.`FlowNote` AS `FlowNote`,
   0 AS `ListType`,
   `a`.`Sender` AS `Sender`,
   `a`.`AtPara` AS `AtPara`,
   `a`.`Domain` AS `Domain`,
   `a`.`OrgNo` AS `OrgNo`,
   `c`.`Idx` AS `FlowIdx`,
   `d`.`Idx` AS `FlowSortIdx`
FROM
   ((( `wf_generworkflow` `a` JOIN `wf_generworkerlist` `b` ) JOIN `wf_flow` `c` ) JOIN `wf_flowsort` `d` )
WHERE
   ((
            `b`.`IsEnable` = 1
        )
       AND ( `b`.`IsPass` = 0 )
       AND ( `a`.`WorkID` = `b`.`WorkID` )
       AND ( `a`.`FK_Node` = `b`.`FK_Node` )
       AND ( `a`.`WFState` <> 0 )
       AND ( `b`.`WhoExeIt` <> 1 )
       AND ( `a`.`FK_Flow` = `c`.`No` )
       AND ( `a`.`FK_FlowSort` = `d`.`No` )
       AND ( `c`.`FK_FlowSort` = `d`.`No` )) UNION
SELECT
   `a`.`PRI` AS `PRI`,
   `a`.`WorkID` AS `WorkID`,
   `b`.`Sta` AS `IsRead`,
   `a`.`Starter` AS `Starter`,
   `a`.`StarterName` AS `StarterName`,
   2 AS `WFState`,
   `a`.`FK_Dept` AS `FK_Dept`,
   `a`.`DeptName` AS `DeptName`,
   '' AS `TodoEmpDeptNo`,
   `a`.`FK_Flow` AS `FK_Flow`,
   `a`.`FlowName` AS `FlowName`,
   `a`.`PWorkID` AS `PWorkID`,
   `a`.`PFlowNo` AS `PFlowNo`,
   `b`.`FK_Node` AS `FK_Node`,
   `b`.`NodeName` AS `NodeName`,
   `a`.`Title` AS `Title`,
   `a`.`RDT` AS `RDT`,
   `b`.`RDT` AS `ADT`,
   `b`.`RDT` AS `SDT`,
   `b`.`CCTo` AS `FK_Emp`,
   `b`.`FID` AS `FID`,
   `a`.`FK_FlowSort` AS `FK_FlowSort`,
   `a`.`SysType` AS `SysType`,
   `a`.`SDTOfNode` AS `SDTOfNode`,
   0 AS `PressTimes`,
   `a`.`GuestNo` AS `GuestNo`,
   `a`.`GuestName` AS `GuestName`,
   `a`.`BillNo` AS `BillNo`,
   `a`.`TodoEmps` AS `TodoEmps`,
   `a`.`TodoEmpsNum` AS `TodoEmpsNum`,
   0 AS `TodoSta`,
   0 AS `TaskSta`,
   `a`.`FlowNote` AS `FlowNote`,
   1 AS `ListType`,
   `b`.`Rec` AS `Sender`,((
                                  0 <> '@IsCC=1'
                              )
   OR ( 0 <> `a`.`AtPara` )) AS `AtPara`,
   `a`.`Domain` AS `Domain`,
   `a`.`OrgNo` AS `OrgNo`,
   `c`.`Idx` AS `FlowIdx`,
   `d`.`Idx` AS `FlowSortIdx`
FROM
   ((( `wf_generworkflow` `a` JOIN `wf_cclist` `b` ) JOIN `wf_flow` `c` ) JOIN `wf_flowsort` `d` )
WHERE
   ((
            `a`.`WorkID` = `b`.`WorkID`
        )
       AND ( `b`.`Sta` <= 1 )
       AND ( `b`.`InEmpWorks` = 1 )
       AND ( `a`.`WFState` <> 0 )
       AND ( `a`.`FK_Flow` = `c`.`No` )
       AND ( `a`.`FK_FlowSort` = `d`.`No` )
       AND ( `c`.`FK_FlowSort` = `d`.`No` ));