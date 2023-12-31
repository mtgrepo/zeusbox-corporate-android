/*
 *
 * Nextcloud Android client application
 *
 * @author Tobias Kaminsky
 * @author TSI-mc
 * Copyright (C) 2020 Tobias Kaminsky
 * Copyright (C) 2020 Nextcloud GmbH
 * Copyright (C) 2021 TSI-mc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.owncloud.android.ui.adapter;

import com.zeuscloud.client.account.User;
import com.owncloud.android.lib.resources.shares.OCShare;

public interface ShareeListAdapterListener {
    void copyLink(OCShare share);

    void showSharingMenuActionSheet(OCShare share);

    void copyInternalLink();

    void createPublicShareLink();

    void createSecureFileDrop();

    void requestPasswordForShare(OCShare share, boolean askForPassword);

    void showPermissionsDialog(OCShare share);

    void showProfileBottomSheet(User user, String shareWith);
}
