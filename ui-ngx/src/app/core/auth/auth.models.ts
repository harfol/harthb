import { AuthUser, User } from '@shared/models/user.model';

export interface AuthPayload {
  authUser: AuthUser;
  userDetails: User;
  userTokenAccessEnabled: boolean;
  allowedDashboardIds: string[];
  forceFullscreen: boolean;
}

export interface AuthState {
  isAuthenticated: boolean;
  isUserLoaded: boolean;
  authUser: AuthUser;
  userDetails: User;
  userTokenAccessEnabled: boolean;
  allowedDashboardIds: string[];
  forceFullscreen: boolean;
  lastPublicDashboardId: string;
}
