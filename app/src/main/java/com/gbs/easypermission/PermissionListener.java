package com.gbs.easypermission;

import java.util.List;

/**
 * @author gdy
 * @date 3/16/21
 */
public interface PermissionListener {
    void onGranted();
    void onDenied(List<String> deniedPermission);
}
