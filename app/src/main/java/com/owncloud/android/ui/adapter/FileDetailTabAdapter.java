/*
 * Nextcloud Android client application
 *
 * @author Andy Scherzinger
 * Copyright (C) 2018 Andy Scherzinger
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU AFFERO GENERAL PUBLIC LICENSE
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU AFFERO GENERAL PUBLIC LICENSE for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.owncloud.android.ui.adapter;

import com.zeuscloud.client.account.User;
import com.zeuscloud.ui.ImageDetailFragment;
import com.owncloud.android.datamodel.OCFile;
import com.owncloud.android.ui.fragment.FileDetailActivitiesFragment;
import com.owncloud.android.ui.fragment.FileDetailSharingFragment;
import com.owncloud.android.utils.EncryptionUtils;
import com.owncloud.android.utils.MimeTypeUtil;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * File details pager adapter.
 */
public class FileDetailTabAdapter extends FragmentStatePagerAdapter {
    private final OCFile file;
    private final User user;

    private FileDetailSharingFragment fileDetailSharingFragment;
    private FileDetailActivitiesFragment fileDetailActivitiesFragment;
    private ImageDetailFragment imageDetailFragment;

    public FileDetailTabAdapter(FragmentManager fm, OCFile file, User user) {
        super(fm);
        this.file = file;
        this.user = user;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            default:
                fileDetailActivitiesFragment = FileDetailActivitiesFragment.newInstance(file, user);
                return fileDetailActivitiesFragment;
            case 1:
                fileDetailSharingFragment = FileDetailSharingFragment.newInstance(file, user);
                return fileDetailSharingFragment;
            case 2:
                imageDetailFragment = ImageDetailFragment.newInstance(file, user);
                return imageDetailFragment;
        }
    }

    public FileDetailSharingFragment getFileDetailSharingFragment() {
        return fileDetailSharingFragment;
    }

    public FileDetailActivitiesFragment getFileDetailActivitiesFragment() {
        return fileDetailActivitiesFragment;
    }

    public ImageDetailFragment getImageDetailFragment() {
        return imageDetailFragment;
    }

    @Override
    public int getCount() {
        if (file.isEncrypted()) {
            if (EncryptionUtils.supportsSecureFiledrop(file, user)) {
                return 2;
            }
            // sharing not allowed for encrypted files, thus only show first tab (activities)
            return 1;
        }
        // unencrypted files/folders
        if (MimeTypeUtil.isImage(file)) {
            return 3;
        }
        return 2;
    }
}
