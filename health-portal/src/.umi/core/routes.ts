// @ts-nocheck
import React from 'react';
import { ApplyPluginsType, dynamic } from 'F:/ProjectSpace/GitHub/personal-health/health-portal/node_modules/@umijs/runtime';
import * as umiExports from './umiExports';
import { plugin } from './plugin';
import LoadingComponent from '@/components/PageLoading/index';

export function getRoutes() {
  const routes = [
  {
    "path": "/user",
    "component": dynamic({ loader: () => import(/* webpackChunkName: 'layouts__UserLayout' */'F:/ProjectSpace/GitHub/personal-health/health-portal/src/layouts/UserLayout'), loading: LoadingComponent}),
    "routes": [
      {
        "name": "login",
        "path": "/user/login",
        "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__user__login' */'F:/ProjectSpace/GitHub/personal-health/health-portal/src/pages/user/login'), loading: LoadingComponent}),
        "exact": true
      }
    ]
  },
  {
    "path": "/",
    "component": dynamic({ loader: () => import(/* webpackChunkName: 'layouts__SecurityLayout' */'F:/ProjectSpace/GitHub/personal-health/health-portal/src/layouts/SecurityLayout'), loading: LoadingComponent}),
    "routes": [
      {
        "path": "/",
        "component": dynamic({ loader: () => import(/* webpackChunkName: 'layouts__BasicLayout' */'F:/ProjectSpace/GitHub/personal-health/health-portal/src/layouts/BasicLayout'), loading: LoadingComponent}),
        "routes": [
          {
            "path": "/index.html",
            "redirect": "/welcome",
            "exact": true
          },
          {
            "path": "/",
            "redirect": "/welcome",
            "exact": true
          },
          {
            "path": "/welcome",
            "name": "welcome",
            "icon": "smile",
            "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__Welcome' */'F:/ProjectSpace/GitHub/personal-health/health-portal/src/pages/Welcome'), loading: LoadingComponent}),
            "exact": true
          },
          {
            "path": "/admin",
            "name": "admin",
            "icon": "crown",
            "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__Admin' */'F:/ProjectSpace/GitHub/personal-health/health-portal/src/pages/Admin'), loading: LoadingComponent}),
            "routes": [
              {
                "path": "/admin/sub-page",
                "name": "sub-page",
                "icon": "smile",
                "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__Welcome' */'F:/ProjectSpace/GitHub/personal-health/health-portal/src/pages/Welcome'), loading: LoadingComponent}),
                "exact": true
              }
            ]
          },
          {
            "name": "list.table-list",
            "icon": "table",
            "path": "/list",
            "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__ListTableList' */'F:/ProjectSpace/GitHub/personal-health/health-portal/src/pages/ListTableList'), loading: LoadingComponent}),
            "exact": true
          },
          {
            "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__404' */'F:/ProjectSpace/GitHub/personal-health/health-portal/src/pages/404'), loading: LoadingComponent}),
            "exact": true
          }
        ]
      },
      {
        "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__404' */'F:/ProjectSpace/GitHub/personal-health/health-portal/src/pages/404'), loading: LoadingComponent}),
        "exact": true
      }
    ]
  },
  {
    "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__404' */'F:/ProjectSpace/GitHub/personal-health/health-portal/src/pages/404'), loading: LoadingComponent}),
    "exact": true
  }
];

  // allow user to extend routes
  plugin.applyPlugins({
    key: 'patchRoutes',
    type: ApplyPluginsType.event,
    args: { routes },
  });

  return routes;
}
