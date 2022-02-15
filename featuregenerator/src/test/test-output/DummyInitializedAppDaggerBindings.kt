package com.americanexpress.android.intl.app.dagger.initialization

import com.americanexpress.android.amexpay.barcode.BarcodePaymentActivity
import com.americanexpress.android.amexpay.barcode.paymentdetails.CardChooserDialog
import com.americanexpress.android.amexpay.view.flow.Phase.EmptyPhaseFragment
import com.americanexpress.android.amexpay.view.flow.PhaseActivity
import com.americanexpress.android.amexpay.view.flow.PhaseFragment
import com.americanexpress.android.authn.mvi.AuthNWebViewFragment
import com.americanexpress.android.autopay.AutoPayConfirmationActivity
import com.americanexpress.android.autopay.AutoPayConfirmationFragment
import com.americanexpress.android.autopay.AutoPayEnrollmentActivity
import com.americanexpress.android.autopay.AutoPayEnrollmentFragment
import com.americanexpress.android.autopay.AutoPayEnrollmentReviewActivity
import com.americanexpress.android.autopay.AutoPayEnrollmentReviewFragment
import com.americanexpress.android.autopay.AutoPayStrategyActivity
import com.americanexpress.android.autopay.AutoPayStrategyFragment
import com.americanexpress.android.autopay.paymentwarnings.PaymentWarningsActivity
import com.americanexpress.android.autopay.paymentwarnings.PaymentWarningsFragment
import com.americanexpress.android.benefits.BenefitsActivity
import com.americanexpress.android.biometricsregistration.BiometricsRegistrationActivity
import com.americanexpress.android.cardactivation.CardActivationActivity
import com.americanexpress.android.cardactivation.cardenrollment.CardActivationDetailsFragment
import com.americanexpress.android.cardactivation.cardenrollment.CardActivationRegistrationActivity
import com.americanexpress.android.cardactivation.cardenrollment.CardActivationVerifyFragment
import com.americanexpress.android.cardactivation.cardenrollment.CardEnrollmentAddCardActivity
import com.americanexpress.android.cardactivation.cardenrollment.CardOTPOptionsFragment
import com.americanexpress.android.cardactivation.cardenrollment.CardOTPVerifyFragment
import com.americanexpress.android.cardactivation.cardenrollment.CardRegistrationContactFragment
import com.americanexpress.android.cardactivation.cardenrollment.CardRegistrationFragment
import com.americanexpress.android.cardactivation.cardenrollment.CardRegistrationPersonalSecurityKeyAnswerFragment
import com.americanexpress.android.cardactivation.cardenrollment.CardRegistrationPersonalSecurityKeyListFragment
import com.americanexpress.android.cardactivation.cardenrollment.LoadingFragment
import com.americanexpress.android.cardactivation.cardenrollment.ManageCardActivity
import com.americanexpress.android.cardactivation.cardenrollment.RemoveCardActivity
import com.americanexpress.android.cardactivation.cardenrollment.RemoveCardConfirmActivity
import com.americanexpress.android.cardactivation.cardenrollment.RemoveCardConfirmFragment
import com.americanexpress.android.cardactivation.cardenrollment.RemoveCardFragment
import com.americanexpress.android.cardactivation.cardenrollment.RemoveCardSuccessActivity
import com.americanexpress.android.cardactivation.cardenrollment.RemoveCardSuccessFragment
import com.americanexpress.android.cardactivation.cardenrollment.account.password.ChooseUserPasswordFragment
import com.americanexpress.android.cardactivation.cardenrollment.account.userid.ChooseUserIdFragment
import com.americanexpress.android.cardactivation.cardenrollment.confirmation.CardEnrollmentConfirmationActivity
import com.americanexpress.android.cardactivation.cardenrollment.confirmation.CardEnrollmentConfirmationFragment
import com.americanexpress.android.cardactivation.cardenrollment.error.ErrorScreenFragment
import com.americanexpress.android.cardactivation.cardenrollment.pin.PinErrorFragment
import com.americanexpress.android.cardfreeze.CardFreezeActivity
import com.americanexpress.android.checkspendingpower.ApprovedDeclinedView
import com.americanexpress.android.checkspendingpower.SpendingPowerActivity
import com.americanexpress.android.checkspendingpower.SpendingPowerFragment
import com.americanexpress.android.checkspendingpower.SpendingPowerResultActivity
import com.americanexpress.android.checkspendingpower.SpendingPowerResultFragment
import com.americanexpress.android.creditlineincrease.CreditLineIncreaseActivity
import com.americanexpress.android.defaultcard.DefaultCardActivity
import com.americanexpress.android.defaultcard.SuccessDefaultCardActivity
import com.americanexpress.android.dialog.BaseDialogFragment
import com.americanexpress.android.dialog.MoneyInputDialogFragment
import com.americanexpress.android.dialog.singlechoice.SingleChoiceAlertDialogBaseFragment
import com.americanexpress.android.digitalchargeverification.credit.PushCreditConfirmFragment
import com.americanexpress.android.digitalchargeverification.fraud.PushFraudConfirmFragment
import com.americanexpress.android.digitalchargeverification.view.CardVerificationActivity
import com.americanexpress.android.digitalchargeverification.view.CardVerificationFragment
import com.americanexpress.android.digitalchargeverification.view.PushConfirmActivity
import com.americanexpress.android.digitalchargeverification.view.PushConfirmFragment
import com.americanexpress.android.digitalchargeverification.view.PushConfirmedActivity
import com.americanexpress.android.digitalchargeverification.view.PushConfirmedFragment
import com.americanexpress.android.dining.booking.BookingConfirmedActivity
import com.americanexpress.android.dining.booking.BookingHistoryActivity
import com.americanexpress.android.dining.booking.BookingReviewActivity
import com.americanexpress.android.dining.citypicker.CityPickerActivity
import com.americanexpress.android.dining.featured.FeaturedRestaurantListActivity
import com.americanexpress.android.dining.home.DiningHomeActivity
import com.americanexpress.android.dining.intro.DiningIntroActivity
import com.americanexpress.android.dining.restaurant.PreferredLocationDialogFragment
import com.americanexpress.android.dining.restaurant.RestaurantDetailActivity
import com.americanexpress.android.dining.search.DiningSearchActivity
import com.americanexpress.android.directdebit.DirectDebitConfirmationActivity
import com.americanexpress.android.directdebit.DirectDebitConfirmationFragment
import com.americanexpress.android.directdebit.DirectDebitEditAccountFragment
import com.americanexpress.android.directdebit.DirectDebitEditActivity
import com.americanexpress.android.directdebit.DirectDebitEditOptionsFragment
import com.americanexpress.android.directdebit.DirectDebitOverviewActivity
import com.americanexpress.android.directdebit.DirectDebitOverviewFragment
import com.americanexpress.android.directdebit.DirectDebitReviewActivity
import com.americanexpress.android.directdebit.DirectDebitReviewFragment
import com.americanexpress.android.disputes.DisputeActivity
import com.americanexpress.android.disputes.disputecenter.DisputeCenterActivity
import com.americanexpress.android.disputes.tooltip.DisputeInfoActivity
import com.americanexpress.android.editemail.EditEmailActivity
import com.americanexpress.android.editname.EditNameActivity
import com.americanexpress.android.editphone.EditPhoneActivity
import com.americanexpress.android.errorretry.ErrorRetryActivity
import com.americanexpress.android.featureintros.FeatureIntroPagerActivity
import com.americanexpress.android.featureintros.IntroActivity
import com.americanexpress.android.featureintros.smsreductionintro.SmsUnregisterConfirmationActivity
import com.americanexpress.android.financials.FinancialsTabView
import com.americanexpress.android.help.activity.FAQActivity
import com.americanexpress.android.incomecapture.IncomeCaptureActivity
import com.americanexpress.android.intl.app.feature.initialization.PostInitHelper
import com.americanexpress.android.intl.app.feature.servicing.ServicingActivity
import com.americanexpress.android.intl.app.feature.servicing.callus.CallUsActivity
import com.americanexpress.android.intl.app.feature.servicing.findyouranswer.FindYourAnswerActivity
import com.americanexpress.android.intl.app.feature.servicing.findyouranswer.FindYourAnswerDetailActivity
import com.americanexpress.android.intl.app.view.BaseTabView
import com.americanexpress.android.intl.app.view.activity.BaseActivity
import com.americanexpress.android.intl.app.view.activity.ChangePasswordActivity
import com.americanexpress.android.intl.app.view.activity.EULAActivity
import com.americanexpress.android.intl.app.view.activity.FAQDetailActivity
import com.americanexpress.android.intl.app.view.activity.FAQWebViewActivity
import com.americanexpress.android.intl.app.view.activity.IntroScreenActivity
import com.americanexpress.android.intl.app.view.activity.LoginActivity
import com.americanexpress.android.login.LoginActivityV2
import com.americanexpress.android.intl.app.view.activity.WebViewActivity
import com.americanexpress.android.intl.app.view.amexpay.AmexPayErrorActivity
import com.americanexpress.android.intl.app.view.amexpay.AmexPayErrorFragment
import com.americanexpress.android.intl.app.view.amexpay.AmexPayHelpActivity
import com.americanexpress.android.intl.app.view.amexpay.CardSelectorActivity
import com.americanexpress.android.intl.app.view.amexpay.CardSelectorFragment
import com.americanexpress.android.intl.app.view.amexpay.ProvisionedCardsActivity
import com.americanexpress.android.intl.app.view.amexpay.ProvisionedCardsFragment
import com.americanexpress.android.intl.app.view.amexpay.TapNPayActivity
import com.americanexpress.android.intl.app.view.amexpay.TapNPayAgainFragment
import com.americanexpress.android.intl.app.view.amexpay.TapNPayFailureFragment
import com.americanexpress.android.intl.app.view.amexpay.TapNPayFragment
import com.americanexpress.android.intl.app.view.amexpay.TapNPaySuccessFragment
import com.americanexpress.android.intl.app.view.amexpay.provisioning.AmexPayProvisioningActivity
import com.americanexpress.android.intl.app.view.amexpay.provisioningflow.AmexPayHelpFragment
import com.americanexpress.android.intl.app.view.fragment.BaseFragment
import com.americanexpress.android.intl.app.view.fragment.BaseLocationAwareFragment
import com.americanexpress.android.intl.app.view.fragment.BaseLoginFragment
import com.americanexpress.android.intl.app.view.fragment.ChangePasswordFragment
import com.americanexpress.android.intl.app.view.fragment.EmptyFragment
import com.americanexpress.android.intl.app.view.fragment.IntroFragment
import com.americanexpress.android.intl.app.view.fragment.IntroScreenFragment
import com.americanexpress.android.intl.app.view.fragment.LegalTextFragment
import com.americanexpress.android.intl.app.view.fragment.LoginFragment
import com.americanexpress.android.intl.app.view.fragment.ProgressDialogFragment
import com.americanexpress.android.intl.app.view.fragment.ReAuthFragment
import com.americanexpress.android.intl.app.view.fragment.TwoStepLoginFragment
import com.americanexpress.android.intl.app.view.fragment.WebViewFragment
import com.americanexpress.android.languageselection.LanguageSelectionActivity
import com.americanexpress.android.loungefinder.LoungeFinderActivity
import com.americanexpress.android.loungefinder.terms.LoungeTermsActivity
import com.americanexpress.android.loungefinder.terms.LoungeTermsFragment
import com.americanexpress.android.maintab.MainTabActivity
import com.americanexpress.android.maintab.tabs.AccountTabView
import com.americanexpress.android.maintab.tabs.OverviewTabView
import com.americanexpress.android.maintab.tabs.membership.RewardsTabView
import com.americanexpress.android.membergetmember.MemberGetMemberActivity
import com.americanexpress.android.membergetmember.MemberGetMemberFragment
import com.americanexpress.android.membership.GiftCardWalletActivity
import com.americanexpress.android.membership.GlobalMembershipTabView
import com.americanexpress.android.membership.activityDetail.MembershipDetailActivity
import com.americanexpress.android.membership.bonusdetail.BonusDetailActivity
import com.americanexpress.android.membership.cashback.CashbackActivity
import com.americanexpress.android.membership.extrabenefit.PeriodTextFragment
import com.americanexpress.android.membership.extrabenefit.PointsTrackerActivity
import com.americanexpress.android.membership.extrabenefit.PointsTrackerFragment
import com.americanexpress.android.membership.extrabenefit.TransactionsActivity
import com.americanexpress.android.membership.extrabenefit.TransactionsFragment
import com.americanexpress.android.membership.rewards.RewardsActivity
import com.americanexpress.android.ndlfaq.NdlFaqActivity
import com.americanexpress.android.oauthgrant.OAuthGrantAccessActivity
import com.americanexpress.android.offers.OffersActivity
import com.americanexpress.android.offers.intl.ExploreNearbyOffersActivity
import com.americanexpress.android.offers.intl.ExploreNearbyOffersFragment
import com.americanexpress.android.offers.intl.MyOffersActivity
import com.americanexpress.android.offers.intl.MyOffersFragment
import com.americanexpress.android.offers.intl.NearbyOffersRow
import com.americanexpress.android.offers.intl.OfferDetailActivity
import com.americanexpress.android.offers.intl.OfferDetailFragment
import com.americanexpress.android.offers.intl.OfferDetailMapFragment
import com.americanexpress.android.offers.intl.OfferTermsActivity
import com.americanexpress.android.offers.intl.OfferTermsFragment
import com.americanexpress.android.offers.intl.OffersTabView
import com.americanexpress.android.payments.PaymentsActivity
import com.americanexpress.android.paymentsoptions.PDFReceiptService
import com.americanexpress.android.paymentsoptions.Payment3DSecureActivity
import com.americanexpress.android.paymentsoptions.Payment3DSecureFragment
import com.americanexpress.android.paymentsoptions.PaymentHistoryActivity
import com.americanexpress.android.paymentsoptions.PaymentHistoryFragment
import com.americanexpress.android.paymentsoptions.PaymentOptionsActivity
import com.americanexpress.android.paymentsoptions.PaymentOptionsFragment
import com.americanexpress.android.paymentsoptions.PaymentOutcomeActivity
import com.americanexpress.android.paymentsoptions.PaymentOutcomeFragment
import com.americanexpress.android.paymentsoptions.PaymentReviewActivity
import com.americanexpress.android.paymentsoptions.PaymentReviewFragment
import com.americanexpress.android.paymentsoptions.PaymentRoutingActivity
import com.americanexpress.android.paymentsoptions.PaymentRoutingFragment
import com.americanexpress.android.paymentsoptions.ScheduledPaymentFragment
import com.americanexpress.android.paymentsoptions.ScheduledPaymentsActivity
import com.americanexpress.android.payyourway.DippAmountToDeferFragment
import com.americanexpress.android.payyourway.DippConfirmPlanFragment
import com.americanexpress.android.payyourway.DippCreatePlanFragment
import com.americanexpress.android.payyourway.DippPlanSuccessFragment
import com.americanexpress.android.payyourway.DippPlansActivity
import com.americanexpress.android.payyourway.PlanManagementActivity
import com.americanexpress.android.payyourway.PlanManagementFragment
import com.americanexpress.android.pinmanagement.PinManagementActivity
import com.americanexpress.android.pinmanagement.choose.ChoosePinFragment
import com.americanexpress.android.pinmanagement.choose.ConfirmPinFragment
import com.americanexpress.android.predictivesearch.PredictiveSearchFragment
import com.americanexpress.android.predictivesearch.filters.panel.multiselection.MultiSelectionFilterActivity
import com.americanexpress.android.profile.ProfileActivity
import com.americanexpress.android.profile.view.ProfileSelectCardDialogFragment
import com.americanexpress.android.referafriend.view.ReferAFriendConfirmationDialog
import com.americanexpress.android.referafriend.view.ReferAFriendSendEmailActivity
import com.americanexpress.android.referafriend.view.ReferAFriendSendEmailFragment
import com.americanexpress.android.referafriendv2.info.ReferAFriendInfoActivity
import com.americanexpress.android.referafriendv2.offer.ReferAFriendV2Activity
import com.americanexpress.android.referafriendv2.result.ShareViaAmexErrorActivity
import com.americanexpress.android.referafriendv2.result.ShareViaAmexSuccessActivity
import com.americanexpress.android.referafriendv2.shareviaamex.ShareViaAmexActivity
import com.americanexpress.android.safekey.SafeKeyWebViewActivity
import com.americanexpress.android.ssowebview.SsoWebViewActivity
import com.americanexpress.android.transactiondetails.TransactionDetailsActivity
import com.americanexpress.android.transactiondetails.TransactionDetailsFragment
import com.americanexpress.android.transactiondetails.TransactionDetailsMapActivity
import com.americanexpress.android.transactionsearch.TransactionSearchActivity
import com.americanexpress.android.usepoints.UsePointsActivity
import com.americanexpress.android.usepoints.UsePointsCategoryFragment
import com.americanexpress.android.usepoints.UsePointsErrorDetailActivity
import com.americanexpress.android.usepoints.UsePointsErrorDetailFragment
import com.americanexpress.android.usepoints.UsePointsHistoryFragment
import com.americanexpress.android.usepoints.UsePointsTransactionDetailsActivity
import com.americanexpress.android.usepoints.UsePointsTransactionDetailsFragment
import com.americanexpress.android.usepoints.UsePointsTransactionsActivity
import com.americanexpress.android.usepoints.UsePointsTransactionsFragment
import com.americanexpress.android.view.MapCardFragment
import com.americanexpress.android.view.webview.PostWebViewActivity
import com.americanexpress.android.view.webview.StaticContentWebViewActivity
import com.americanexpress.android.view.webview.StaticContentWebViewFragment
import com.americanexpress.biometricauth.fingerprint.EnableFingerprintFragment
import com.americanexpress.biometricauth.fingerprint.EnrolmentResultFragment
import com.americanexpress.biometricauth.fingerprint.FingerprintEnrollWizard
import com.americanexpress.biometricauth.fingerprint.FingerprintInfoActivity
import com.americanexpress.biometricauth.fingerprint.FingerprintIntroFragment
import com.americanexpress.biometricauth.fingerprint.FingerprintSettingsActivity
import com.americanexpress.dagger.injectorWrapper
import com.americanexpress.pushnotifications.CardNotificationDetailFragment
import com.americanexpress.pushnotifications.CardNotificationDetailsActivity
import com.americanexpress.pushnotifications.CardNotificationListFragment
import com.americanexpress.pushnotifications.CardNotificationsActivity
import com.americanexpress.supplementaryspendview.BreakdownActivity
import com.americanexpress.timeline.TimelineContentView
import io.aexp.otp.OtpActivity
import io.aexp.peertopeer.PeerToPeerActivity
import io.aexp.planitV2.ui.createplan.htmlterms.PlanItHtmlTermsActivity

interface InitializedAppDaggerBindings {
    fun inject(paymentsActivity: PaymentsActivity)
    fun inject(baseActivity: BaseActivity)
    fun inject(baseTabView: BaseTabView)
    fun inject(overviewTabView: OverviewTabView)
    fun inject(offersTabView: OffersTabView)
    fun inject(financialsTabView: FinancialsTabView)
    fun inject(activity: BreakdownActivity)
    fun inject(rewardsTabView: RewardsTabView)
    fun inject(globalMembershipTabView: GlobalMembershipTabView)
    fun inject(accountTabView: AccountTabView)
    fun inject(fingerprintInfoModal: FingerprintInfoActivity)
    fun inject(fingerprintSettingsActivity: FingerprintSettingsActivity)
    fun inject(dDOverviewActivity: DirectDebitOverviewActivity)
    fun inject(paymentHistoryActivity: PaymentHistoryActivity)
    fun inject(eULAActivity: EULAActivity)
    fun inject(phaseActivity: PhaseActivity)
    fun inject(amexPayErrorActivity: AmexPayErrorActivity)
    fun inject(amexPayErrorFragment: AmexPayErrorFragment)
    fun inject(provisionedCardsActivity: ProvisionedCardsActivity)
    fun inject(amexPayProvisioningActivity: AmexPayProvisioningActivity)
    fun inject(barcodePaymentActivity: BarcodePaymentActivity)
    fun inject(cardChooserDialog: CardChooserDialog)
    fun inject(paymentReviewActivity: PaymentReviewActivity)
    fun inject(cardNotificationsActivity: CardNotificationsActivity)
    fun inject(myOffersActivity: MyOffersActivity)
    fun inject(cardNotificationDetailsActivity: CardNotificationDetailsActivity)
    fun inject(bonusDetailActivity: BonusDetailActivity)
    fun inject(paymentOutcomeActivity: PaymentOutcomeActivity)
    fun inject(baseNavActivity: MainTabActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(loginActivity: LoginActivityV2)
    fun inject(manageCardActivity: ManageCardActivity)
    fun inject(faqWebViewActivity: FAQWebViewActivity)
    fun inject(faqActivity: FAQActivity)
    fun inject(faqDetailActivity: FAQDetailActivity)
    fun inject(webViewActivity: WebViewActivity)
    fun inject(scheduledPaymentsActivity: ScheduledPaymentsActivity)
    fun inject(changePasswordActivity: ChangePasswordActivity)
    fun inject(cardSelectorActivity: CardSelectorActivity)
    fun inject(paymentOptionsActivity: PaymentOptionsActivity)
    fun inject(offerTermsActivity: OfferTermsActivity)
    fun inject(offerDetailActivity: OfferDetailActivity)
    fun inject(exploreNearbyOffersActivity: ExploreNearbyOffersActivity)
    fun inject(cardActivationRegistrationActivity: CardActivationRegistrationActivity)
    fun inject(payment3DSecureActivity: Payment3DSecureActivity)
    fun inject(removeCardActivity: RemoveCardActivity)
    fun inject(cardEnrollmentAddCardActivity: CardEnrollmentAddCardActivity)
    fun inject(tapNPayActivity: TapNPayActivity)
    fun inject(introActivity: IntroActivity)
    fun inject(fingerprintEnrollWizard: FingerprintEnrollWizard)
    fun inject(removeCardConfirmActivity: RemoveCardConfirmActivity)
    fun inject(removeCardSuccessActivity: RemoveCardSuccessActivity)
    fun inject(mGMActivity: MemberGetMemberActivity)
    fun inject(activity: ShareViaAmexActivity)
    fun inject(activity: ReferAFriendV2Activity)
    fun inject(activity: ShareViaAmexErrorActivity)
    fun inject(activity: ShareViaAmexSuccessActivity)
    fun inject(activity: ReferAFriendInfoActivity)
    fun inject(dDEditActivity: DirectDebitEditActivity)
    fun inject(dDReviewActivity: DirectDebitReviewActivity)
    fun inject(dDConfirmationActivity: DirectDebitConfirmationActivity)
    fun inject(editNameActivity: EditNameActivity)
    fun inject(amexPayHelpActivity: AmexPayHelpActivity)
    fun inject(loungeFinderActivity: LoungeFinderActivity)
    fun inject(loungeTermsActivity: LoungeTermsActivity)
    fun inject(spendingPowerActivity: SpendingPowerActivity)
    fun inject(spendingPowerResultActivity: SpendingPowerResultActivity)
    fun inject(approvedDeclinedView: ApprovedDeclinedView)
    fun inject(cardVerificationActivity: CardVerificationActivity)
    fun inject(pushConfirmActivity: PushConfirmActivity)
    fun inject(pushConfirmedActivity: PushConfirmedActivity)
    fun inject(usePointsActivity: UsePointsActivity)
    fun inject(usePointsErrorDetailActivity: UsePointsErrorDetailActivity)
    fun inject(usePointsTransactionDetailsActivity: UsePointsTransactionDetailsActivity)
    fun inject(usePointsTransactionsActivity: UsePointsTransactionsActivity)
    fun inject(referAFriendSendEmailActivity: ReferAFriendSendEmailActivity)
    fun inject(autoPayStrategyActivity: AutoPayStrategyActivity)
    fun inject(autoPayConfirmationActivity: AutoPayConfirmationActivity)
    fun inject(autoPayEnrollmentActivity: AutoPayEnrollmentActivity)
    fun inject(autoPayEnrollmentReviewActivity: AutoPayEnrollmentReviewActivity)
    fun inject(paymentWarningsActivity: PaymentWarningsActivity)
    fun inject(staticContentWebViewActivity: StaticContentWebViewActivity)
    fun inject(pointsTrackerActivity: PointsTrackerActivity)
    fun inject(transactionsActivity: TransactionsActivity)
    fun inject(giftCardWalletActivity: GiftCardWalletActivity)
    fun inject(planItHtmlTermsActivity: PlanItHtmlTermsActivity)
    fun inject(paymentRoutingActivity: PaymentRoutingActivity)
    fun inject(baseDialogFragment: BaseDialogFragment)
    fun inject(progressDialogFragment: ProgressDialogFragment)
    fun inject(singleChoiceAlertDialogBaseFragment: SingleChoiceAlertDialogBaseFragment)
    fun inject(baseFragment: BaseFragment)
    fun inject(eULAFragment: LegalTextFragment)
    fun inject(paymentOutcomeFragment: PaymentOutcomeFragment)
    fun inject(removeCardFragment: RemoveCardFragment)
    fun inject(cardRegistrationPersonalSecurityKeyListFragment: CardRegistrationPersonalSecurityKeyListFragment)
    fun inject(cardSelectorFragment: CardSelectorFragment)
    fun inject(offerTermsFragment: OfferTermsFragment)
    fun inject(phaseFragment: PhaseFragment)
    fun inject(tapNPayFragment: TapNPayFragment)
    fun inject(tapNPayAgainFragment: TapNPayAgainFragment)
    fun inject(tapNPayFailureFragment: TapNPayFailureFragment)
    fun inject(tapNPaySuccessFragment: TapNPaySuccessFragment)
    fun inject(emptyPhaseFragment: EmptyPhaseFragment)
    fun inject(provisionedCardsFragment: ProvisionedCardsFragment)
    fun inject(introFragment: IntroFragment)
    fun inject(fingerprintIntroFragment: FingerprintIntroFragment)
    fun inject(enrolmentResultFragment: EnrolmentResultFragment)
    fun inject(enableFingerprintFragment: EnableFingerprintFragment)
    fun inject(dDEditAccountFragment: DirectDebitEditAccountFragment)
    fun inject(removeCardSuccessFragment: RemoveCardSuccessFragment)
    fun inject(removeCardConfirmFragment: RemoveCardConfirmFragment)
    fun inject(payment3DSecureFragment: Payment3DSecureFragment)
    fun inject(dDReviewFragment: DirectDebitReviewFragment)
    fun inject(changePasswordFragment: ChangePasswordFragment)
    fun inject(timelineFragment: TimelineContentView)
    fun inject(myOffersFragment: MyOffersFragment)
    fun inject(cardActivationDetailsFragment: CardActivationDetailsFragment)
    fun inject(reAuthFragment: ReAuthFragment)
    fun inject(cardActivationVerifyFragment: CardActivationVerifyFragment)
    fun inject(paymentReviewFragment: PaymentReviewFragment)
    fun inject(offerDetailFragment: OfferDetailFragment)
    fun inject(offerDetailMapFragment: OfferDetailMapFragment)
    fun inject(exploreNearbyOffersFragment: ExploreNearbyOffersFragment)
    fun inject(cardOTPOptionsFragment: CardOTPOptionsFragment)
    fun inject(cardNotificationListFragment: CardNotificationListFragment)
    fun inject(cardRegistrationPersonalSecurityKeyAnswerFragment: CardRegistrationPersonalSecurityKeyAnswerFragment)
    fun inject(cardOTPVerifyFragment: CardOTPVerifyFragment)
    fun inject(paymentOptionsFragment: PaymentOptionsFragment)
    fun inject(baseLocationAwareFragment: BaseLocationAwareFragment)
    fun inject(mapCardFragment: MapCardFragment)
    fun inject(cardRegistrationContactFragment: CardRegistrationContactFragment)
    fun inject(paymentHistoryFragment: PaymentHistoryFragment)
    fun inject(scheduledPaymentFragment: ScheduledPaymentFragment)
    fun inject(cardRegistrationFragment: CardRegistrationFragment)
    fun inject(dDOverviewFragment: DirectDebitOverviewFragment)
    fun inject(baseLoginFragment: BaseLoginFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(twoStepLoginFragment: TwoStepLoginFragment)
    fun inject(webViewFragment: WebViewFragment)
    fun inject(dDConfirmationFragment: DirectDebitConfirmationFragment)
    fun inject(amexPayHelpFragment: AmexPayHelpFragment)
    fun inject(dDEditOptionsFragment: DirectDebitEditOptionsFragment)
    fun inject(loungeTermsFragment: LoungeTermsFragment)
    fun inject(spendingPowerFragment: SpendingPowerFragment)
    fun inject(SpendingPowerResultFragment: SpendingPowerResultFragment)
    fun inject(cardNotificationDetailFragment: CardNotificationDetailFragment)
    fun inject(mGMFragment: MemberGetMemberFragment)
    fun inject(cardVerificationFragment: CardVerificationFragment)
    fun inject(pushConfirmFragment: PushConfirmFragment)
    fun inject(pushConfirmedFragment: PushConfirmedFragment)
    fun inject(pushFraudConfirmFragment: PushFraudConfirmFragment)
    fun inject(pushCreditConfirmFragment: PushCreditConfirmFragment)
    fun inject(emptyFragment: EmptyFragment)
    fun inject(usePointsHistoryFragment: UsePointsHistoryFragment)
    fun inject(usePointsCategoryFragment: UsePointsCategoryFragment)
    fun inject(usePointsTransactionDetailsFragment: UsePointsTransactionDetailsFragment)
    fun inject(usePointsTransactionsFragment: UsePointsTransactionsFragment)
    fun inject(usePointsErrorDetailFragment: UsePointsErrorDetailFragment)
    fun inject(referAFriendSendEmailFragment: ReferAFriendSendEmailFragment)
    fun inject(referAFriendConfirmationDialog: ReferAFriendConfirmationDialog)
    fun inject(autoPayConfirmationFragment: AutoPayConfirmationFragment)
    fun inject(autoPayEnrollmentFragment: AutoPayEnrollmentFragment)
    fun inject(autoPayEnrollmentReviewFragment: AutoPayEnrollmentReviewFragment)
    fun inject(autoPayStrategyFragment: AutoPayStrategyFragment)
    fun inject(paymentWarningsFragment: PaymentWarningsFragment)
    fun inject(staticContentWebViewFragment: StaticContentWebViewFragment)
    fun inject(pointsTrackerFragment: PointsTrackerFragment)
    fun inject(periodTextFragment: PeriodTextFragment)
    fun inject(transactionFragment: TransactionsFragment)
    fun inject(moneyInputDialogFragment: MoneyInputDialogFragment)
    fun inject(cashbackActivity: CashbackActivity)
    fun inject(paymentRoutingFragment: PaymentRoutingFragment)
    fun inject(transactionDetailsActivity: TransactionDetailsActivity)
    fun inject(transactionDetailsMapActivity: TransactionDetailsMapActivity)
    fun inject(transactionDetailsFragment: TransactionDetailsFragment)
    fun inject(servicingActivity: ServicingActivity)
    fun inject(findYourAnswerActivity: FindYourAnswerActivity)
    fun inject(findYourAnswerDetailActivity: FindYourAnswerDetailActivity)
    fun inject(callUsActivity: CallUsActivity)
    fun inject(featureIntroPagerActivity: FeatureIntroPagerActivity)
    fun inject(smsUnregisterConfirmationActivity: SmsUnregisterConfirmationActivity)
    fun inject(choosePinFragment: ChoosePinFragment)
    fun inject(confirmPinFragment: ConfirmPinFragment)
    fun inject(pinErrorFragment: PinErrorFragment)
    fun inject(cardEnrollmentConfirmationActivity: CardEnrollmentConfirmationActivity)
    fun inject(cardEnrollmentConfirmationFragment: CardEnrollmentConfirmationFragment)
    fun inject(loadingFragment: LoadingFragment)
    fun inject(authNWebViewFragment: AuthNWebViewFragment)
    fun inject(dippConfirmPlanFragment: DippConfirmPlanFragment)
    fun inject(dippAmountToDeferFragment: DippAmountToDeferFragment)
    fun inject(dippCreatePlanFragment: DippCreatePlanFragment)
    fun inject(dippPlansActivity: DippPlansActivity)
    fun inject(dippPlanSuccessFragment: DippPlanSuccessFragment)
    fun inject(planManagementActivity: PlanManagementActivity)
    fun inject(planManagementFragment: PlanManagementFragment)
    fun inject(disputeActivity: DisputeActivity)
    fun inject(diningHomeActivity: DiningHomeActivity)
    fun inject(cityPickerActivity: CityPickerActivity)
    fun inject(activity: PostWebViewActivity)
    fun inject(diningIntroActivity: DiningIntroActivity)
    fun inject(diningSearchActivity: DiningSearchActivity)
    fun inject(predictiveSearchFragment: PredictiveSearchFragment)
    fun inject(restaurantDetailActivity: RestaurantDetailActivity)
    fun inject(bookingReviewActivity: BookingReviewActivity)
    fun inject(bookingConfirmedActivity: BookingConfirmedActivity)
    fun inject(featuredRestaurantListActivity: FeaturedRestaurantListActivity)
    fun inject(bookingHistoryActivity: BookingHistoryActivity)
    fun inject(preferredLocationDialogFragment: PreferredLocationDialogFragment)
    fun inject(disputeInfoActivity: DisputeInfoActivity)
    fun inject(disputeCenterActivity: DisputeCenterActivity)
    fun inject(editEmailActivity: EditEmailActivity)
    fun inject(creditLineIncreaseActivity: CreditLineIncreaseActivity)
    fun inject(pinManagementActivity: PinManagementActivity)
    fun inject(cardFreezeActivity: CardFreezeActivity)
    fun inject(rewardsActivity: RewardsActivity)
    fun inject(activity: MembershipDetailActivity)
    fun inject(activity: NdlFaqActivity)
    fun inject(nearbyOffersRow: NearbyOffersRow)
    fun inject(ssoWebViewActivity: SsoWebViewActivity)
    fun inject(safeKeyWebViewActivity: SafeKeyWebViewActivity)
    fun inject(activity: DefaultCardActivity)
    fun inject(activity: SuccessDefaultCardActivity)
    fun inject(multiSelectionFilterActivity: MultiSelectionFilterActivity)
    fun inject(activity: ProfileActivity)
    fun inject(profileSelectCardDialogFragment: ProfileSelectCardDialogFragment)
    fun inject(activity: IncomeCaptureActivity)
    fun inject(activity: PeerToPeerActivity)
    fun inject(activity: io.aexp.peertopeer.v2.PeerToPeerActivity)
    fun inject(activity: BenefitsActivity)
    fun inject(activity: OtpActivity)
    fun inject(pdfReceiptService: PDFReceiptService)
    fun inject(activity: OffersActivity)
    fun inject(fragment: ChooseUserIdFragment)
    fun inject(fragment: ChooseUserPasswordFragment)
    fun inject(activity: EditPhoneActivity)
    fun inject(postInitHelper: PostInitHelper)
    fun inject(fragment: ErrorScreenFragment)
    fun inject(activity: IntroScreenActivity)
    fun inject(fragment: IntroScreenFragment)
    fun inject(activity: CardActivationActivity)
    fun inject(activity: BiometricsRegistrationActivity)
    fun inject(activity: TransactionSearchActivity)
    fun inject(activity: LanguageSelectionActivity)
    fun inject(activity: OAuthGrantAccessActivity)
    fun inject(activity: ErrorRetryActivity)
}

fun initializedAppInjector(bindings: InitializedAppDaggerBindings) = injectorWrapper(bindings) {
    bind(PaymentsActivity::class) { bindings.inject(it) }
    bind(BaseActivity::class) { bindings.inject(it) }
    bind(BaseTabView::class) { bindings.inject(it) }
    bind(OverviewTabView::class) { bindings.inject(it) }
    bind(OffersTabView::class) { bindings.inject(it) }
    bind(FinancialsTabView::class) { bindings.inject(it) }
    bind(BreakdownActivity::class) { bindings.inject(it) }
    bind(RewardsTabView::class) { bindings.inject(it) }
    bind(GlobalMembershipTabView::class) { bindings.inject(it) }
    bind(AccountTabView::class) { bindings.inject(it) }
    bind(FingerprintInfoActivity::class) { bindings.inject(it) }
    bind(FingerprintSettingsActivity::class) { bindings.inject(it) }
    bind(DirectDebitOverviewActivity::class) { bindings.inject(it) }
    bind(PaymentHistoryActivity::class) { bindings.inject(it) }
    bind(EULAActivity::class) { bindings.inject(it) }
    bind(PhaseActivity::class) { bindings.inject(it) }
    bind(AmexPayErrorActivity::class) { bindings.inject(it) }
    bind(AmexPayErrorFragment::class) { bindings.inject(it) }
    bind(ProvisionedCardsActivity::class) { bindings.inject(it) }
    bind(AmexPayProvisioningActivity::class) { bindings.inject(it) }
    bind(BarcodePaymentActivity::class) { bindings.inject(it) }
    bind(CardChooserDialog::class) { bindings.inject(it) }
    bind(PaymentReviewActivity::class) { bindings.inject(it) }
    bind(CardNotificationsActivity::class) { bindings.inject(it) }
    bind(MyOffersActivity::class) { bindings.inject(it) }
    bind(CardNotificationDetailsActivity::class) { bindings.inject(it) }
    bind(BonusDetailActivity::class) { bindings.inject(it) }
    bind(PaymentOutcomeActivity::class) { bindings.inject(it) }
    bind(MainTabActivity::class) { bindings.inject(it) }
    bind(LoginActivity::class) { bindings.inject(it) }
    bind(LoginActivityV2::class) { bindings.inject(it) }
    bind(ManageCardActivity::class) { bindings.inject(it) }
    bind(EditNameActivity::class) { bindings.inject(it) }
    bind(FAQWebViewActivity::class) { bindings.inject(it) }
    bind(FAQActivity::class) { bindings.inject(it) }
    bind(FAQDetailActivity::class) { bindings.inject(it) }
    bind(WebViewActivity::class) { bindings.inject(it) }
    bind(ScheduledPaymentsActivity::class) { bindings.inject(it) }
    bind(ChangePasswordActivity::class) { bindings.inject(it) }
    bind(CardSelectorActivity::class) { bindings.inject(it) }
    bind(PaymentOptionsActivity::class) { bindings.inject(it) }
    bind(OfferTermsActivity::class) { bindings.inject(it) }
    bind(OfferDetailActivity::class) { bindings.inject(it) }
    bind(ExploreNearbyOffersActivity::class) { bindings.inject(it) }
    bind(CardActivationRegistrationActivity::class) { bindings.inject(it) }
    bind(Payment3DSecureActivity::class) { bindings.inject(it) }
    bind(RemoveCardActivity::class) { bindings.inject(it) }
    bind(CardEnrollmentAddCardActivity::class) { bindings.inject(it) }
    bind(TapNPayActivity::class) { bindings.inject(it) }
    bind(IntroActivity::class) { bindings.inject(it) }
    bind(FingerprintEnrollWizard::class) { bindings.inject(it) }
    bind(RemoveCardConfirmActivity::class) { bindings.inject(it) }
    bind(RemoveCardSuccessActivity::class) { bindings.inject(it) }
    bind(MemberGetMemberActivity::class) { bindings.inject(it) }
    bind(ShareViaAmexActivity::class) { bindings.inject(it) }
    bind(ReferAFriendV2Activity::class) { bindings.inject(it) }
    bind(ShareViaAmexErrorActivity::class) { bindings.inject(it) }
    bind(ShareViaAmexSuccessActivity::class) { bindings.inject(it) }
    bind(ReferAFriendInfoActivity::class) { bindings.inject(it) }
    bind(DirectDebitEditActivity::class) { bindings.inject(it) }
    bind(DirectDebitReviewActivity::class) { bindings.inject(it) }
    bind(DirectDebitConfirmationActivity::class) { bindings.inject(it) }
    bind(AmexPayHelpActivity::class) { bindings.inject(it) }
    bind(LoungeFinderActivity::class) { bindings.inject(it) }
    bind(LoungeTermsActivity::class) { bindings.inject(it) }
    bind(SpendingPowerActivity::class) { bindings.inject(it) }
    bind(SpendingPowerResultActivity::class) { bindings.inject(it) }
    bind(CardVerificationActivity::class) { bindings.inject(it) }
    bind(PushConfirmActivity::class) { bindings.inject(it) }
    bind(PushConfirmedActivity::class) { bindings.inject(it) }
    bind(UsePointsActivity::class) { bindings.inject(it) }
    bind(UsePointsErrorDetailActivity::class) { bindings.inject(it) }
    bind(UsePointsTransactionDetailsActivity::class) { bindings.inject(it) }
    bind(UsePointsTransactionsActivity::class) { bindings.inject(it) }
    bind(ReferAFriendSendEmailActivity::class) { bindings.inject(it) }
    bind(AutoPayStrategyActivity::class) { bindings.inject(it) }
    bind(AutoPayConfirmationActivity::class) { bindings.inject(it) }
    bind(AutoPayEnrollmentActivity::class) { bindings.inject(it) }
    bind(AutoPayEnrollmentReviewActivity::class) { bindings.inject(it) }
    bind(PaymentWarningsActivity::class) { bindings.inject(it) }
    bind(StaticContentWebViewActivity::class) { bindings.inject(it) }
    bind(PointsTrackerActivity::class) { bindings.inject(it) }
    bind(TransactionsActivity::class) { bindings.inject(it) }
    bind(GiftCardWalletActivity::class) { bindings.inject(it) }
    bind(PlanItHtmlTermsActivity::class) { bindings.inject(it) }
    bind(PaymentRoutingActivity::class) { bindings.inject(it) }
    bind(BaseDialogFragment::class) { bindings.inject(it) }
    bind(ProgressDialogFragment::class) { bindings.inject(it) }
    bind(SingleChoiceAlertDialogBaseFragment::class) { bindings.inject(it) }
    bind(BaseFragment::class) { bindings.inject(it) }
    bind(LegalTextFragment::class) { bindings.inject(it) }
    bind(PaymentOutcomeFragment::class) { bindings.inject(it) }
    bind(RemoveCardFragment::class) { bindings.inject(it) }
    bind(CardRegistrationPersonalSecurityKeyListFragment::class) { bindings.inject(it) }
    bind(CardSelectorFragment::class) { bindings.inject(it) }
    bind(OfferTermsFragment::class) { bindings.inject(it) }
    bind(PhaseFragment::class) { bindings.inject(it) }
    bind(TapNPayFragment::class) { bindings.inject(it) }
    bind(TapNPayAgainFragment::class) { bindings.inject(it) }
    bind(TapNPayFailureFragment::class) { bindings.inject(it) }
    bind(TapNPaySuccessFragment::class) { bindings.inject(it) }
    bind(EmptyPhaseFragment::class) { bindings.inject(it) }
    bind(ProvisionedCardsFragment::class) { bindings.inject(it) }
    bind(IntroFragment::class) { bindings.inject(it) }
    bind(FingerprintIntroFragment::class) { bindings.inject(it) }
    bind(EnrolmentResultFragment::class) { bindings.inject(it) }
    bind(EnableFingerprintFragment::class) { bindings.inject(it) }
    bind(DirectDebitEditAccountFragment::class) { bindings.inject(it) }
    bind(RemoveCardSuccessFragment::class) { bindings.inject(it) }
    bind(RemoveCardConfirmFragment::class) { bindings.inject(it) }
    bind(Payment3DSecureFragment::class) { bindings.inject(it) }
    bind(DirectDebitReviewFragment::class) { bindings.inject(it) }
    bind(ChangePasswordFragment::class) { bindings.inject(it) }
    bind(TimelineContentView::class) { bindings.inject(it) }
    bind(MyOffersFragment::class) { bindings.inject(it) }
    bind(CardActivationDetailsFragment::class) { bindings.inject(it) }
    bind(ReAuthFragment::class) { bindings.inject(it) }
    bind(CardActivationVerifyFragment::class) { bindings.inject(it) }
    bind(PaymentReviewFragment::class) { bindings.inject(it) }
    bind(OfferDetailFragment::class) { bindings.inject(it) }
    bind(OfferDetailMapFragment::class) { bindings.inject(it) }
    bind(ExploreNearbyOffersFragment::class) { bindings.inject(it) }
    bind(CardOTPOptionsFragment::class) { bindings.inject(it) }
    bind(CardNotificationListFragment::class) { bindings.inject(it) }
    bind(CardRegistrationPersonalSecurityKeyAnswerFragment::class) { bindings.inject(it) }
    bind(CardOTPVerifyFragment::class) { bindings.inject(it) }
    bind(PaymentOptionsFragment::class) { bindings.inject(it) }
    bind(BaseLocationAwareFragment::class) { bindings.inject(it) }
    bind(MapCardFragment::class) { bindings.inject(it) }
    bind(CardRegistrationContactFragment::class) { bindings.inject(it) }
    bind(PaymentHistoryFragment::class) { bindings.inject(it) }
    bind(ScheduledPaymentFragment::class) { bindings.inject(it) }
    bind(CardRegistrationFragment::class) { bindings.inject(it) }
    bind(DirectDebitOverviewFragment::class) { bindings.inject(it) }
    bind(BaseLoginFragment::class) { bindings.inject(it) }
    bind(LoginFragment::class) { bindings.inject(it) }
    bind(TwoStepLoginFragment::class) { bindings.inject(it) }
    bind(WebViewFragment::class) { bindings.inject(it) }
    bind(DirectDebitConfirmationFragment::class) { bindings.inject(it) }
    bind(AmexPayHelpFragment::class) { bindings.inject(it) }
    bind(DirectDebitEditOptionsFragment::class) { bindings.inject(it) }
    bind(LoungeTermsFragment::class) { bindings.inject(it) }
    bind(SpendingPowerFragment::class) { bindings.inject(it) }
    bind(SpendingPowerResultFragment::class) { bindings.inject(it) }
    bind(ApprovedDeclinedView::class) { bindings.inject(it) }
    bind(CardNotificationDetailFragment::class) { bindings.inject(it) }
    bind(MemberGetMemberFragment::class) { bindings.inject(it) }
    bind(CardVerificationFragment::class) { bindings.inject(it) }
    bind(PushConfirmFragment::class) { bindings.inject(it) }
    bind(PushConfirmedFragment::class) { bindings.inject(it) }
    bind(PushFraudConfirmFragment::class) { bindings.inject(it) }
    bind(PushCreditConfirmFragment::class) { bindings.inject(it) }
    bind(EmptyFragment::class) { bindings.inject(it) }
    bind(UsePointsHistoryFragment::class) { bindings.inject(it) }
    bind(UsePointsCategoryFragment::class) { bindings.inject(it) }
    bind(UsePointsTransactionDetailsFragment::class) { bindings.inject(it) }
    bind(UsePointsTransactionsFragment::class) { bindings.inject(it) }
    bind(UsePointsErrorDetailFragment::class) { bindings.inject(it) }
    bind(ReferAFriendSendEmailFragment::class) { bindings.inject(it) }
    bind(ReferAFriendConfirmationDialog::class) { bindings.inject(it) }
    bind(AutoPayConfirmationFragment::class) { bindings.inject(it) }
    bind(AutoPayEnrollmentFragment::class) { bindings.inject(it) }
    bind(AutoPayEnrollmentReviewFragment::class) { bindings.inject(it) }
    bind(AutoPayStrategyFragment::class) { bindings.inject(it) }
    bind(PaymentWarningsFragment::class) { bindings.inject(it) }
    bind(StaticContentWebViewFragment::class) { bindings.inject(it) }
    bind(PointsTrackerFragment::class) { bindings.inject(it) }
    bind(PeriodTextFragment::class) { bindings.inject(it) }
    bind(TransactionsFragment::class) { bindings.inject(it) }
    bind(MoneyInputDialogFragment::class) { bindings.inject(it) }
    bind(CashbackActivity::class) { bindings.inject(it) }
    bind(PaymentRoutingFragment::class) { bindings.inject(it) }
    bind(TransactionDetailsActivity::class) { bindings.inject(it) }
    bind(TransactionDetailsMapActivity::class) { bindings.inject(it) }
    bind(TransactionDetailsFragment::class) { bindings.inject(it) }
    bind(ServicingActivity::class) { bindings.inject(it) }
    bind(FindYourAnswerActivity::class) { bindings.inject(it) }
    bind(FindYourAnswerDetailActivity::class) { bindings.inject(it) }
    bind(CallUsActivity::class) { bindings.inject(it) }
    bind(FeatureIntroPagerActivity::class) { bindings.inject(it) }
    bind(SmsUnregisterConfirmationActivity::class) { bindings.inject(it) }
    bind(ChoosePinFragment::class) { bindings.inject(it) }
    bind(ConfirmPinFragment::class) { bindings.inject(it) }
    bind(CardEnrollmentConfirmationActivity::class) { bindings.inject(it) }
    bind(CardEnrollmentConfirmationFragment::class) { bindings.inject(it) }
    bind(LoadingFragment::class) { bindings.inject(it) }
    bind(AuthNWebViewFragment::class) { bindings.inject(it) }
    bind(PinErrorFragment::class) { bindings.inject(it) }
    bind(DippConfirmPlanFragment::class) { bindings.inject(it) }
    bind(DippAmountToDeferFragment::class) { bindings.inject(it) }
    bind(DippCreatePlanFragment::class) { bindings.inject(it) }
    bind(DippPlansActivity::class) { bindings.inject(it) }
    bind(DippPlanSuccessFragment::class) { bindings.inject(it) }
    bind(PlanManagementActivity::class) { bindings.inject(it) }
    bind(PlanManagementFragment::class) { bindings.inject(it) }
    bind(DisputeActivity::class) { bindings.inject(it) }
    bind(DiningHomeActivity::class) { bindings.inject(it) }
    bind(CityPickerActivity::class) { bindings.inject(it) }
    bind(PostWebViewActivity::class) { bindings.inject(it) }
    bind(DiningIntroActivity::class) { bindings.inject(it) }
    bind(DiningSearchActivity::class) { bindings.inject(it) }
    bind(PredictiveSearchFragment::class) { bindings.inject(it) }
    bind(RestaurantDetailActivity::class) { bindings.inject(it) }
    bind(BookingReviewActivity::class) { bindings.inject(it) }
    bind(BookingConfirmedActivity::class) { bindings.inject(it) }
    bind(FeaturedRestaurantListActivity::class) { bindings.inject(it) }
    bind(BookingHistoryActivity::class) { bindings.inject(it) }
    bind(PreferredLocationDialogFragment::class) { bindings.inject(it) }
    bind(DisputeInfoActivity::class) { bindings.inject(it) }
    bind(DisputeCenterActivity::class) { bindings.inject(it) }
    bind(EditEmailActivity::class) { bindings.inject(it) }
    bind(PinManagementActivity::class) { bindings.inject(it) }
    bind(CardFreezeActivity::class) { bindings.inject(it) }
    bind(RewardsActivity::class) { bindings.inject(it) }
    bind(SsoWebViewActivity::class) { bindings.inject(it) }
    bind(MembershipDetailActivity::class) { bindings.inject(it) }
    bind(NdlFaqActivity::class) { bindings.inject(it) }
    bind(NearbyOffersRow::class) { bindings.inject(it) }
    bind(ProfileActivity::class) { bindings.inject(it) }
    bind(SafeKeyWebViewActivity::class) { bindings.inject(it) }
    bind(DefaultCardActivity::class) { bindings.inject(it) }
    bind(SuccessDefaultCardActivity::class) { bindings.inject(it) }
    bind(MultiSelectionFilterActivity::class) { bindings.inject(it) }
    bind(ProfileActivity::class) { bindings.inject(it) }
    bind(ProfileSelectCardDialogFragment::class) { bindings.inject(it) }
    bind(IncomeCaptureActivity::class) { bindings.inject(it) }
    bind(PeerToPeerActivity::class) { bindings.inject(it) }
    bind(io.aexp.peertopeer.v2.PeerToPeerActivity::class) { bindings.inject(it) }
    bind(CreditLineIncreaseActivity::class) { bindings.inject(it) }
    bind(BenefitsActivity::class) { bindings.inject(it) }
    bind(OtpActivity::class) { bindings.inject(it) }
    bind(PDFReceiptService::class) { bindings.inject(it) }
    bind(OffersActivity::class) { bindings.inject(it) }
    bind(ChooseUserIdFragment::class) { bindings.inject(it) }
    bind(ChooseUserPasswordFragment::class) { bindings.inject(it) }
    bind(EditPhoneActivity::class) { bindings.inject(it) }
    bind(ErrorScreenFragment::class) { bindings.inject(it) }
    bind(IntroScreenActivity::class) { bindings.inject(it) }
    bind(IntroScreenFragment::class) { bindings.inject(it) }
    bind(CardActivationActivity::class) { bindings.inject(it) }
    bind(BiometricsRegistrationActivity::class) { bindings.inject(it) }
    bind(TransactionSearchActivity::class) { bindings.inject(it) }
    bind(LanguageSelectionActivity::class) { bindings.inject(it) }
    bind(OAuthGrantAccessActivity::class) { bindings.inject(it) }
    bind(ErrorRetryActivity::class) { bindings.inject(it) }
}
