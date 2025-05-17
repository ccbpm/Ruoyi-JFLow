import{userNodes as tt,routeNodes as et,ccNodes as it,subFlowNodes as nt,labelNodes as ot}from"./x6Shapes-dE1R6_8e.js";import{V as I,e as _,b as h,G as $,d as M,f as A,g as P,h as st,r as O,j as V,R as rt,k as X,t as at,l as Q,M as j,m as T,n as S,o as G,N as J,p as H}from"./html-CzRSfd_o.js";import{o as lt}from"./position-DbjHVEQ7.js";import{ca as ct}from"./antd-C8r6Ue4p.js";import{u as gt}from"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";const dt=`.x6-widget-dnd {
  position: absolute;
  top: -10000px;
  left: -10000px;
  z-index: 999999;
  display: none;
  cursor: move;
  opacity: 0.7;
  pointer-events: 'cursor';
}
.x6-widget-dnd.dragging {
  display: inline-block;
}
.x6-widget-dnd.dragging * {
  pointer-events: none !important;
}
.x6-widget-dnd .x6-graph {
  background: transparent;
  box-shadow: none;
}
`;var ht=function(l,t,e,i){var o=arguments.length,n=o<3?t:i===null?i=Object.getOwnPropertyDescriptor(t,e):i,s;if(typeof Reflect=="object"&&typeof Reflect.decorate=="function")n=Reflect.decorate(l,t,e,i);else for(var r=l.length-1;r>=0;r--)(s=l[r])&&(n=(o<3?s(n):o>3?s(t,e,n):s(t,e))||n);return o>3&&n&&Object.defineProperty(t,e,n),n};class w extends I{get targetScroller(){return this.options.target.getPlugin("scroller")}get targetGraph(){return this.options.target}get targetModel(){return this.targetGraph.model}get snapline(){return this.options.target.getPlugin("snapline")}constructor(t){super(),this.name="dnd",this.options=Object.assign(Object.assign({},w.defaults),t),this.init()}init(){_(this.name,dt),this.container=document.createElement("div"),h(this.container,this.prefixClassName("widget-dnd")),this.draggingGraph=new $(Object.assign(Object.assign({},this.options.delegateGraphOptions),{container:document.createElement("div"),width:1,height:1,async:!1})),M(this.container,this.draggingGraph.container)}start(t,e){const i=e;i.preventDefault(),this.targetModel.startBatch("dnd"),h(this.container,"dragging"),A(this.container,this.options.draggingContainer||document.body),this.sourceNode=t,this.prepareDragging(t,i.clientX,i.clientY);const o=this.updateNodePosition(i.clientX,i.clientY);this.isSnaplineEnabled()&&(this.snapline.captureCursorOffset({e:i,node:t,cell:t,view:this.draggingView,x:o.x,y:o.y}),this.draggingNode.on("change:position",this.snap,this)),this.delegateDocumentEvents(w.documentEvents,i.data)}isSnaplineEnabled(){return this.snapline&&this.snapline.isEnabled()}prepareDragging(t,e,i){const o=this.draggingGraph,n=o.model,s=this.options.getDragNode(t,{sourceNode:t,draggingGraph:o,targetGraph:this.targetGraph});s.position(0,0);let r=5;if(this.isSnaplineEnabled()&&(r+=this.snapline.options.tolerance||0),this.isSnaplineEnabled()||this.options.scaled){const g=this.targetGraph.transform.getScale();o.scale(g.sx,g.sy),r*=Math.max(g.sx,g.sy)}else o.scale(1,1);this.clearDragging(),n.resetCells([s]);const a=o.findViewByCell(s);a.undelegateEvents(),a.cell.off("changed"),o.fitToContent({padding:r,allowNewOrigin:"any",useCellGeometry:!1});const c=a.getBBox();this.geometryBBox=a.getBBox({useCellGeometry:!0}),this.delta=this.geometryBBox.getTopLeft().diff(c.getTopLeft()),this.draggingNode=s,this.draggingView=a,this.draggingBBox=s.getBBox(),this.padding=r,this.originOffset=this.updateGraphPosition(e,i)}updateGraphPosition(t,e){const i=document.body.scrollTop||document.documentElement.scrollTop,o=document.body.scrollLeft||document.documentElement.scrollLeft,n=this.delta,s=this.geometryBBox,r=this.padding||5,a={left:t-n.x-s.width/2-r+o,top:e-n.y-s.height/2-r+i};return this.draggingGraph&&P(this.container,{left:`${a.left}px`,top:`${a.top}px`}),a}updateNodePosition(t,e){const i=this.targetGraph.clientToLocal(t,e),o=this.draggingBBox;return i.x-=o.width/2,i.y-=o.height/2,this.draggingNode.position(i.x,i.y),i}snap({cell:t,current:e,options:i}){const o=t;if(i.snapped){const n=this.draggingBBox;o.position(n.x+i.tx,n.y+i.ty,{silent:!0}),this.draggingView.translate(),o.position(e.x,e.y,{silent:!0}),this.snapOffset={x:i.tx,y:i.ty}}else this.snapOffset=null}onDragging(t){const e=this.draggingView;if(e){t.preventDefault();const i=this.normalizeEvent(t),o=i.clientX,n=i.clientY;this.updateGraphPosition(o,n);const s=this.updateNodePosition(o,n),r=this.targetGraph.options.embedding.enabled,a=(r||this.isSnaplineEnabled())&&this.isInsideValidArea({x:o,y:n});if(r){e.setEventData(i,{graph:this.targetGraph,candidateEmbedView:this.candidateEmbedView});const c=e.getEventData(i);a?e.processEmbedding(i,c):e.clearEmbedding(c),this.candidateEmbedView=c.candidateEmbedView}this.isSnaplineEnabled()&&(a?this.snapline.snapOnMoving({e:i,view:e,x:s.x,y:s.y}):this.snapline.hide())}}onDragEnd(t){const e=this.draggingNode;if(e){const i=this.normalizeEvent(t),o=this.draggingView,n=this.draggingBBox,s=this.snapOffset;let r=n.x,a=n.y;s&&(r+=s.x,a+=s.y),e.position(r,a,{silent:!0});const c=this.drop(e,{x:i.clientX,y:i.clientY}),g=u=>{u?(this.onDropped(e),this.targetGraph.options.embedding.enabled&&o&&(o.setEventData(i,{cell:u,graph:this.targetGraph,candidateEmbedView:this.candidateEmbedView}),o.finalizeEmbedding(i,o.getEventData(i)))):this.onDropInvalid(),this.candidateEmbedView=null,this.targetModel.stopBatch("dnd")};st(c)?(this.undelegateDocumentEvents(),c.then(g)):g(c)}}clearDragging(){this.draggingNode&&(this.sourceNode=null,this.draggingNode.remove(),this.draggingNode=null,this.draggingView=null,this.delta=null,this.padding=null,this.snapOffset=null,this.originOffset=null,this.undelegateDocumentEvents())}onDropped(t){this.draggingNode===t&&(this.clearDragging(),O(this.container,"dragging"),V(this.container))}onDropInvalid(){const t=this.draggingNode;t&&this.onDropped(t)}isInsideValidArea(t){let e,i=null;const o=this.targetGraph,n=this.targetScroller;this.options.dndContainer&&(i=this.getDropArea(this.options.dndContainer));const s=i&&i.containsPoint(t);if(n)if(n.options.autoResize)e=this.getDropArea(n.container);else{const r=this.getDropArea(n.container);e=this.getDropArea(o.container).intersectsWithRect(r)}else e=this.getDropArea(o.container);return!s&&e&&e.containsPoint(t)}getDropArea(t){const e=lt(t),i=document.body.scrollTop||document.documentElement.scrollTop,o=document.body.scrollLeft||document.documentElement.scrollLeft;return rt.create({x:e.left+parseInt(P(t,"border-left-width"),10)-o,y:e.top+parseInt(P(t,"border-top-width"),10)-i,width:t.clientWidth,height:t.clientHeight})}drop(t,e){if(this.isInsideValidArea(e)){const i=this.targetGraph,o=i.model,n=i.clientToLocal(e),s=this.sourceNode,r=this.options.getDropNode(t,{sourceNode:s,draggingNode:t,targetGraph:this.targetGraph,draggingGraph:this.draggingGraph}),a=r.getBBox();n.x+=a.x-a.width/2,n.y+=a.y-a.height/2;const c=this.snapOffset?1:i.getGridSize();r.position(X.snapToGrid(n.x,c),X.snapToGrid(n.y,c)),r.removeZIndex();const g=this.options.validateNode,u=g?g(r,{sourceNode:s,draggingNode:t,droppingNode:r,targetGraph:i,draggingGraph:this.draggingGraph}):!0;return typeof u=="boolean"?u?(o.addCell(r,{stencil:this.cid}),r):null:at(u).then(x=>x?(o.addCell(r,{stencil:this.cid}),r):null)}return null}onRemove(){this.draggingGraph&&(this.draggingGraph.view.remove(),this.draggingGraph.dispose())}dispose(){this.remove(),Q(this.name)}}ht([I.dispose()],w.prototype,"dispose",null);(function(l){l.defaults={getDragNode:t=>t.clone(),getDropNode:t=>t.clone()},l.documentEvents={mousemove:"onDragging",touchmove:"onDragging",mouseup:"onDragEnd",touchend:"onDragEnd",touchcancel:"onDragEnd"}})(w||(w={}));function pt(l,t={}){const e=j.isModel(l)?l:new j().resetCells(l,{sort:!1,dryrun:!0}),i=e.getNodes(),o=t.columns||1,n=Math.ceil(i.length/o),s=t.dx||0,r=t.dy||0,a=t.center!==!1,c=t.resizeToFit===!0,g=t.marginX||0,u=t.marginY||0,x=[];let f=t.columnWidth;if(f==="compact")for(let d=0;d<o;d+=1){const y=m.getNodesInColumn(i,d,o);x.push(m.getMaxDim(y,"width")+s)}else{(f==null||f==="auto")&&(f=m.getMaxDim(i,"width")+s);for(let d=0;d<o;d+=1)x.push(f)}const E=m.accumulate(x,g),L=[];let D=t.rowHeight;if(D==="compact")for(let d=0;d<n;d+=1){const y=m.getNodesInRow(i,d,o);L.push(m.getMaxDim(y,"height")+r)}else{(D==null||D==="auto")&&(D=m.getMaxDim(i,"height")+r);for(let d=0;d<n;d+=1)L.push(D)}const q=m.accumulate(L,u);e.startBatch("layout"),i.forEach((d,y)=>{const W=y%o,U=Math.floor(y/o),R=x[W],z=L[U];let Z=0,Y=0,b=d.getSize();if(c){let B=R-2*s,k=z-2*r;const F=b.height*(b.width?B/b.width:1),K=b.width*(b.height?k/b.height:1);z<F?B=K:k=F,b={width:B,height:k},d.setSize(b,t)}a&&(Z=(R-b.width)/2,Y=(z-b.height)/2),d.position(E[W]+s+Z,q[U]+r+Y,t)}),e.stopBatch("layout")}var m;(function(l){function t(n,s){return n.reduce((r,a)=>Math.max(a==null?void 0:a.getSize()[s],r),0)}l.getMaxDim=t;function e(n,s,r){const a=[];for(let c=r*s,g=c+r;c<g;c+=1)n[c]&&a.push(n[c]);return a}l.getNodesInRow=e;function i(n,s,r){const a=[];for(let c=s,g=n.length;c<g;c+=r)n[c]&&a.push(n[c]);return a}l.getNodesInColumn=i;function o(n,s){return n.reduce((r,a,c)=>(r.push(r[c]+a),r),[s||0])}l.accumulate=o})(m||(m={}));const ut=`.x6-widget-dnd {
  position: absolute;
  top: -10000px;
  left: -10000px;
  z-index: 999999;
  display: none;
  cursor: move;
  opacity: 0.7;
  pointer-events: 'cursor';
}
.x6-widget-dnd.dragging {
  display: inline-block;
}
.x6-widget-dnd.dragging * {
  pointer-events: none !important;
}
.x6-widget-dnd .x6-graph {
  background: transparent;
  box-shadow: none;
}
.x6-widget-stencil {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}
.x6-widget-stencil::after {
  position: absolute;
  top: 0;
  display: block;
  width: 100%;
  height: 20px;
  padding: 8px 0;
  line-height: 20px;
  text-align: center;
  opacity: 0;
  transition: top 0.1s linear, opacity 0.1s linear;
  content: ' ';
  pointer-events: none;
}
.x6-widget-stencil-content {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  height: auto;
  overflow-x: hidden;
  overflow-y: auto;
}
.x6-widget-stencil .x6-node [magnet]:not([magnet='passive']) {
  pointer-events: none;
}
.x6-widget-stencil-group {
  padding: 0;
  padding-bottom: 8px;
  overflow: hidden;
  user-select: none;
}
.x6-widget-stencil-group.collapsed {
  height: auto;
  padding-bottom: 0;
}
.x6-widget-stencil-group-title {
  position: relative;
  margin-top: 0;
  margin-bottom: 0;
  padding: 4px;
  cursor: pointer;
}
.x6-widget-stencil-title,
.x6-widget-stencil-group > .x6-widget-stencil-group-title {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  user-select: none;
}
.x6-widget-stencil .unmatched {
  opacity: 0.3;
}
.x6-widget-stencil .x6-node.unmatched {
  display: none;
}
.x6-widget-stencil-group.unmatched {
  display: none;
}
.x6-widget-stencil-search-text {
  position: relative;
  z-index: 1;
  box-sizing: border-box;
  width: 100%;
  height: 30px;
  max-height: 30px;
  line-height: 30px;
  outline: 0;
}
.x6-widget-stencil.not-found::after {
  opacity: 1;
  content: attr(data-not-found-text);
}
.x6-widget-stencil.not-found.searchable::after {
  top: 30px;
}
.x6-widget-stencil.not-found.searchable.collapsable::after {
  top: 50px;
}
.x6-widget-stencil {
  color: #333;
  background: #f5f5f5;
}
.x6-widget-stencil-content {
  position: absolute;
}
.x6-widget-stencil.collapsable > .x6-widget-stencil-content {
  top: 32px;
}
.x6-widget-stencil.searchable > .x6-widget-stencil-content {
  top: 80px;
}
.x6-widget-stencil.not-found::after {
  position: absolute;
}
.x6-widget-stencil.not-found.searchable.collapsable::after {
  top: 80px;
}
.x6-widget-stencil.not-found.searchable::after {
  top: 60px;
}
.x6-widget-stencil-group {
  height: auto;
  margin-bottom: 1px;
  padding: 0;
  transition: none;
}
.x6-widget-stencil-group .x6-graph {
  background: transparent;
  box-shadow: none;
}
.x6-widget-stencil-group.collapsed {
  height: auto;
  max-height: 31px;
}
.x6-widget-stencil-title,
.x6-widget-stencil-group > .x6-widget-stencil-group-title {
  position: relative;
  left: 0;
  box-sizing: border-box;
  width: 100%;
  height: 32px;
  padding: 0 5px 0 8px;
  color: #666;
  font-weight: 700;
  font-size: 12px;
  line-height: 32px;
  cursor: default;
  transition: all 0.3;
}
.x6-widget-stencil-title:hover,
.x6-widget-stencil-group > .x6-widget-stencil-group-title:hover {
  color: #444;
}
.x6-widget-stencil-title {
  background: #e9e9e9;
}
.x6-widget-stencil-group > .x6-widget-stencil-group-title {
  background: #ededed;
}
.x6-widget-stencil.collapsable > .x6-widget-stencil-title,
.x6-widget-stencil-group.collapsable > .x6-widget-stencil-group-title {
  padding-left: 32px;
  cursor: pointer;
}
.x6-widget-stencil.collapsable > .x6-widget-stencil-title::before,
.x6-widget-stencil-group.collapsable > .x6-widget-stencil-group-title::before {
  position: absolute;
  top: 6px;
  left: 8px;
  display: block;
  width: 18px;
  height: 18px;
  margin: 0;
  padding: 0;
  background-color: transparent;
  background-repeat: no-repeat;
  background-position: 0 0;
  border: none;
  content: ' ';
}
.x6-widget-stencil.collapsable > .x6-widget-stencil-title::before,
.x6-widget-stencil-group.collapsable > .x6-widget-stencil-group-title::before {
  background-image: url('data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTgiIGhlaWdodD0iMTgiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGcgZmlsbD0iIzAwMCIgZmlsbC1ydWxlPSJub256ZXJvIj48cGF0aCBkPSJNOS4zNzUuNUM0LjY4Ny41Ljg3NSA0LjMxMy44NzUgOWMwIDQuNjg4IDMuODEyIDguNSA4LjUgOC41IDQuNjg3IDAgOC41LTMuODEyIDguNS04LjUgMC00LjY4Ny0zLjgxMy04LjUtOC41LTguNXptMCAxNS44ODZDNS4zMDMgMTYuMzg2IDEuOTkgMTMuMDcyIDEuOTkgOXMzLjMxMi03LjM4NSA3LjM4NS03LjM4NVMxNi43NiA0LjkyOCAxNi43NiA5YzAgNC4wNzItMy4zMTMgNy4zODYtNy4zODUgNy4zODZ6Ii8+PHBhdGggZD0iTTEyLjc1MyA4LjQ0M0g1Ljk5N2EuNTU4LjU1OCAwIDAwMCAxLjExNmg2Ljc1NmEuNTU4LjU1OCAwIDAwMC0xLjExNnoiLz48L2c+PC9zdmc+');
  opacity: 0.4;
  transition: all 0.3s;
}
.x6-widget-stencil.collapsable > .x6-widget-stencil-title:hover::before,
.x6-widget-stencil-group.collapsable > .x6-widget-stencil-group-title:hover::before {
  opacity: 0.6;
}
.x6-widget-stencil.collapsable.collapsed > .x6-widget-stencil-title::before,
.x6-widget-stencil-group.collapsable.collapsed > .x6-widget-stencil-group-title::before {
  background-image: url('data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTgiIGhlaWdodD0iMTgiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGcgZmlsbD0iIzAwMCIgZmlsbC1ydWxlPSJub256ZXJvIj48cGF0aCBkPSJNOS4zNzUuNUM0LjY4Ny41Ljg3NSA0LjMxMy44NzUgOWMwIDQuNjg4IDMuODEyIDguNSA4LjUgOC41IDQuNjg3IDAgOC41LTMuODEyIDguNS04LjUgMC00LjY4Ny0zLjgxMy04LjUtOC41LTguNXptMCAxNS44ODZDNS4zMDMgMTYuMzg2IDEuOTkgMTMuMDcyIDEuOTkgOXMzLjMxMi03LjM4NSA3LjM4NS03LjM4NVMxNi43NiA0LjkyOCAxNi43NiA5YzAgNC4wNzItMy4zMTMgNy4zODYtNy4zODUgNy4zODZ6Ii8+PHBhdGggZD0iTTEyLjc1MyA4LjQ0M0g1Ljk5N2EuNTU4LjU1OCAwIDAwMCAxLjExNmg2Ljc1NmEuNTU4LjU1OCAwIDAwMC0xLjExNnoiLz48cGF0aCBkPSJNOC44MTcgNS42MjN2Ni43NTZhLjU1OC41NTggMCAwMDEuMTE2IDBWNS42MjNhLjU1OC41NTggMCAxMC0xLjExNiAweiIvPjwvZz48L3N2Zz4=');
  opacity: 0.4;
}
.x6-widget-stencil.collapsable.collapsed > .x6-widget-stencil-title:hover::before,
.x6-widget-stencil-group.collapsable.collapsed > .x6-widget-stencil-group-title:hover::before {
  opacity: 0.6;
}
.x6-widget-stencil input[type='search'] {
  appearance: textfield;
}
.x6-widget-stencil input[type='search']::-webkit-search-cancel-button,
.x6-widget-stencil input[type='search']::-webkit-search-decoration {
  appearance: none;
}
.x6-widget-stencil-search-text {
  display: block;
  width: 90%;
  margin: 8px 5%;
  padding-left: 8px;
  color: #333;
  background: #fff;
  border: 1px solid #e9e9e9;
  border-radius: 12px;
  outline: 0;
}
.x6-widget-stencil-search-text:focus {
  outline: 0;
}
.x6-widget-stencil::after {
  color: #808080;
  font-weight: 600;
  font-size: 12px;
  background: 0 0;
}
`;var ft=function(l,t,e,i){var o=arguments.length,n=o<3?t:i===null?i=Object.getOwnPropertyDescriptor(t,e):i,s;if(typeof Reflect=="object"&&typeof Reflect.decorate=="function")n=Reflect.decorate(l,t,e,i);else for(var r=l.length-1;r>=0;r--)(s=l[r])&&(n=(o<3?s(n):o>3?s(t,e,n):s(t,e))||n);return o>3&&n&&Object.defineProperty(t,e,n),n};class v extends I{get targetScroller(){return this.options.target.getPlugin("scroller")}get targetGraph(){return this.options.target}get targetModel(){return this.targetGraph.model}constructor(t={}){super(),this.name="stencil",_(this.name,ut),this.graphs={},this.groups={},this.options=Object.assign(Object.assign({},v.defaultOptions),t),this.init()}init(){this.dnd=new w(this.options),this.onSearch=ct(this.onSearch,200),this.initContainer(),this.initSearch(),this.initContent(),this.initGroups(),this.setTitle(),this.startListening()}load(t,e){return Array.isArray(t)?this.loadGroup(t,e):this.options.groups&&Object.keys(this.options.groups).forEach(i=>{t[i]&&this.loadGroup(t[i],i)}),this}unload(t,e){return Array.isArray(t)?this.loadGroup(t,e,!0):this.options.groups&&Object.keys(this.options.groups).forEach(i=>{t[i]&&this.loadGroup(t[i],i,!0)}),this}toggleGroup(t){return this.isGroupCollapsed(t)?this.expandGroup(t):this.collapseGroup(t),this}collapseGroup(t){if(this.isGroupCollapsable(t)){const e=this.groups[t];e&&!this.isGroupCollapsed(t)&&(this.trigger("group:collapse",{name:t}),h(e,"collapsed"))}return this}expandGroup(t){if(this.isGroupCollapsable(t)){const e=this.groups[t];e&&this.isGroupCollapsed(t)&&(this.trigger("group:expand",{name:t}),O(e,"collapsed"))}return this}isGroupCollapsable(t){const e=this.groups[t];return T(e,"collapsable")}isGroupCollapsed(t){const e=this.groups[t];return e&&T(e,"collapsed")}collapseGroups(){return Object.keys(this.groups).forEach(t=>this.collapseGroup(t)),this}expandGroups(){return Object.keys(this.groups).forEach(t=>this.expandGroup(t)),this}resizeGroup(t,e){const i=this.graphs[t];return i&&i.resize(e.width,e.height),this}addGroup(t){const e=Array.isArray(t)?t:[t];this.options.groups?this.options.groups.push(...e):this.options.groups=e,e.forEach(i=>this.initGroup(i))}removeGroup(t){const e=Array.isArray(t)?t:[t];this.options.groups&&(this.options.groups=this.options.groups.filter(i=>!e.includes(i.name)),e.forEach(i=>{this.graphs[i].dispose(),delete this.graphs[i];const n=this.groups[i];V(n),delete this.groups[i]}))}initContainer(){this.container=document.createElement("div"),h(this.container,this.prefixClassName(p.base)),S(this.container,"data-not-found-text",this.options.notFoundText||"No matches found")}initContent(){this.content=document.createElement("div"),h(this.content,this.prefixClassName(p.content)),A(this.content,this.container)}initSearch(){this.options.search&&(h(this.container,"searchable"),M(this.container,this.renderSearch()))}initGroup(t){const e=this.options.stencilGraphOptions||{},i=document.createElement("div");h(i,this.prefixClassName(p.group)),S(i,"data-name",t.name),(t.collapsable==null&&this.options.collapsable||t.collapsable!==!1)&&h(i,"collapsable"),G(i,"collapsed",t.collapsed===!0);const o=document.createElement("h3");h(o,this.prefixClassName(p.groupTitle)),o.innerHTML=t.title||t.name;const n=document.createElement("div");h(n,this.prefixClassName(p.groupContent));const s=t.graphOptions,r=new $(Object.assign(Object.assign(Object.assign({},e),s),{container:document.createElement("div"),model:e.model||new j,width:t.graphWidth||this.options.stencilGraphWidth,height:t.graphHeight||this.options.stencilGraphHeight,interacting:!1,preventDefaultBlankAction:!1}));M(n,r.container),M(i,[o,n]),A(i,this.content),this.groups[t.name]=i,this.graphs[t.name]=r}initGroups(){if(this.clearGroups(),this.setCollapsableState(),this.options.groups&&this.options.groups.length)this.options.groups.forEach(t=>{this.initGroup(t)});else{const t=this.options.stencilGraphOptions||{},e=new $(Object.assign(Object.assign({},t),{container:document.createElement("div"),model:t.model||new j,width:this.options.stencilGraphWidth,height:this.options.stencilGraphHeight,interacting:!1,preventDefaultBlankAction:!1}));M(this.content,e.container),this.graphs[C.defaultGroupName]=e}}setCollapsableState(){this.options.collapsable=this.options.collapsable&&this.options.groups&&this.options.groups.some(t=>t.collapsable!==!1),this.options.collapsable?(h(this.container,"collapsable"),this.options.groups&&this.options.groups.every(e=>e.collapsed||e.collapsable===!1)?h(this.container,"collapsed"):O(this.container,"collapsed")):O(this.container,"collapsable")}setTitle(){const t=document.createElement("div");h(t,this.prefixClassName(p.title)),t.innerHTML=this.options.title,A(t,this.container)}renderSearch(){const t=document.createElement("div");h(t,this.prefixClassName(p.search));const e=document.createElement("input");return S(e,{type:"search",placeholder:this.options.placeholder||"Search"}),h(e,this.prefixClassName(p.searchText)),M(t,e),t}startListening(){const t=this.prefixClassName(p.title),e=this.prefixClassName(p.searchText),i=this.prefixClassName(p.groupTitle);this.delegateEvents({[`click .${t}`]:"onTitleClick",[`touchstart .${t}`]:"onTitleClick",[`click .${i}`]:"onGroupTitleClick",[`touchstart .${i}`]:"onGroupTitleClick",[`input .${e}`]:"onSearch",[`focusin .${e}`]:"onSearchFocusIn",[`focusout .${e}`]:"onSearchFocusOut"}),Object.keys(this.graphs).forEach(o=>{this.graphs[o].on("cell:mousedown",this.onDragStart,this)})}stopListening(){this.undelegateEvents(),Object.keys(this.graphs).forEach(t=>{this.graphs[t].off("cell:mousedown",this.onDragStart,this)})}loadGroup(t,e,i){const o=this.getModel(e);if(o){const a=t.map(c=>J.isNode(c)?c:J.create(c));i===!0?o.removeCells(a):o.resetCells(a)}const n=this.getGroup(e);let s=this.options.stencilGraphHeight;n&&n.graphHeight!=null&&(s=n.graphHeight);const r=n&&n.layout||this.options.layout;if(r&&o&&H(r,this,o,n),!s){const a=this.getGraph(e);a.fitToContent({minWidth:a.options.width,gridHeight:1,padding:n&&n.graphPadding||this.options.stencilGraphPadding||10})}return this}onDragStart(t){const{e,node:i}=t,o=this.getGroupByNode(i);o&&o.nodeMovable===!1||this.dnd.start(i,e)}filter(t,e){const i=Object.keys(this.graphs).reduce((o,n)=>{const s=this.graphs[n],r=n===C.defaultGroupName?null:n,a=s.model.getNodes().filter(x=>{let f=!1;typeof e=="function"?f=H(e,this,x,t,r,this):typeof e=="boolean"?f=e:f=this.isCellMatched(x,t,e,t.toLowerCase()!==t);const E=s.renderer.findViewByCell(x);return E&&G(E.container,"unmatched",!f),f}),c=a.length>0,g=this.options,u=new j;return u.resetCells(a),g.layout&&H(g.layout,this,u,this.getGroup(n)),this.groups[n]&&G(this.groups[n],"unmatched",!c),s.fitToContent({gridWidth:1,gridHeight:1,padding:g.stencilGraphPadding||10}),o||c},!1);G(this.container,"not-found",!i)}isCellMatched(t,e,i,o){return e&&i?Object.keys(i).some(n=>{if(n==="*"||t.shape===n){const s=i[n];return typeof s=="boolean"?s:(Array.isArray(s)?s:[s]).some(a=>{let c=t.getPropByPath(a);return c!=null?(c=`${c}`,o||(c=c.toLowerCase()),c.indexOf(e)>=0):!1})}return!1}):!0}onSearch(t){this.filter(t.target.value,this.options.search)}onSearchFocusIn(){h(this.container,"is-focused")}onSearchFocusOut(){O(this.container,"is-focused")}onTitleClick(){this.options.collapsable&&(G(this.container,"collapsed"),T(this.container,"collapsed")?this.collapseGroups():this.expandGroups())}onGroupTitleClick(t){const e=t.target.closest(`.${this.prefixClassName(p.group)}`);e&&this.toggleGroup(S(e,"data-name")||"");const i=Object.keys(this.groups).every(o=>{const n=this.getGroup(o),s=this.groups[o];return n&&n.collapsable===!1||T(s,"collapsed")});G(this.container,"collapsed",i)}getModel(t){const e=this.getGraph(t);return e?e.model:null}getGraph(t){return this.graphs[t||C.defaultGroupName]}getGroup(t){const e=this.options.groups;return t!=null&&e&&e.length?e.find(i=>i.name===t):null}getGroupByNode(t){const e=this.options.groups;return e?e.find(i=>{const o=this.getModel(i.name);return o?o.has(t.id):!1}):null}clearGroups(){Object.keys(this.graphs).forEach(t=>{this.graphs[t].dispose()}),Object.keys(this.groups).forEach(t=>{const e=this.groups[t];V(e)}),this.graphs={},this.groups={}}onRemove(){this.clearGroups(),this.dnd.remove(),this.stopListening(),this.undelegateDocumentEvents()}dispose(){this.remove(),Q(this.name)}}ft([I.dispose()],v.prototype,"dispose",null);(function(l){l.defaultOptions=Object.assign({stencilGraphWidth:200,stencilGraphHeight:800,title:"Stencil",collapsable:!1,placeholder:"Search",notFoundText:"No matches found",layout(t,e){const i={columnWidth:this.options.stencilGraphWidth/2-10,columns:2,rowHeight:80,resizeToFit:!1,dx:10,dy:10};pt(t,Object.assign(Object.assign(Object.assign({},i),this.options.layoutOptions),e?e.layoutOptions:{}))}},w.defaults)})(v||(v={}));var p;(function(l){l.base="widget-stencil",l.title=`${l.base}-title`,l.search=`${l.base}-search`,l.searchText=`${l.search}-text`,l.content=`${l.base}-content`,l.group=`${l.base}-group`,l.groupTitle=`${l.group}-title`,l.groupContent=`${l.group}-content`})(p||(p={}));var C;(function(l){l.defaultGroupName="__default__"})(C||(C={}));const{t:N}=gt();function xt(l,t,e,i){const o=[];for(const n of e){const s=l.createNode(n);s.markup=void 0,o.push(s)}t.load(o,i)}function Mt(l,t){const e=[{title:`${N("flowdesign.button.usernode")}`,name:"userNode",list:tt},{title:`${N("flowdesign.button.routernode")}`,name:"routeNode",list:et},{title:`${N("flowdesign.button.ccnode")}`,name:"ccNode",list:it},{title:`${N("flowdesign.button.subflownode")}`,name:"subFlowNode",list:nt},{title:`${N("flowdesign.button.label")}`,name:"labelNode",list:ot}],i=new v({title:`${N("flowdesign.button.flowdesigntitle")}`,target:l,stencilGraphWidth:180,stencilGraphHeight:0,collapsable:!1,groups:e.map(({title:o,name:n})=>({title:o,name:n,collapsable:!1})),layoutOptions:{columns:1,columnWidth:220,rowHeight:60}});t==null||t.appendChild(i.container);for(const o of e)xt(l,i,o.list,o.name)}export{Mt as initStencil};
