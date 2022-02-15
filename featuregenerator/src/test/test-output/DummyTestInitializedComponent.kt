package com.americanexpress.android.intl.app.dagger.initialization

import com.americanexpress.android.LogoffTest
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentConfirmActivityTest
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentConfirmedActivityTest
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentFlow1Test
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentFlow2Test
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentFlow3Test
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentFlow5Test
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentFlow6Test
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentFlowTestBase
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentModule
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentSummaryActivity2Test
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentSummaryActivityTest
import com.americanexpress.android.acctsvcs.us.billing.MultipleScheduledPaymentsActivityTest
import com.americanexpress.android.acctsvcs.us.billing.PaymentTestRule
import com.americanexpress.android.acctsvcs.us.billing.ScheduledPaymentDetailsActivityFlowTest
import com.americanexpress.android.acctsvcs.us.billing.ScheduledPaymentDetailsActivityTest
import com.americanexpress.android.acctsvcs.us.dagger.PaymentsUsModule
import com.americanexpress.android.amexpay.AmexPayBarcodePaymentFlowTest
import com.americanexpress.android.amexpay.AmexPayBarcodePaymentNoPinFlowTest
import com.americanexpress.android.amexpay.AmexPayErrorContentTest
import com.americanexpress.android.amexpay.AmexPayErrorTest
import com.americanexpress.android.amexpay.AmexPayFlowTest
import com.americanexpress.android.amexpay.AmexPayLoginFragmentTest
import com.americanexpress.android.amexpay.BarcodePaymentActivityTest
import com.americanexpress.android.amexpay.ProvisionedCardsActivityTest
import com.americanexpress.android.amexpay.TapNPayActivityTest
import com.americanexpress.android.amexpay.provisioning.AmexPayProvisioningFlowTest
import com.americanexpress.android.amexpay.provisioning.view.AmexPayProvisioningLocationPermissionIntroViewTest
import com.americanexpress.android.amexpay.provisioning.view.AmexPayProvisioningSuccessViewTest
import com.americanexpress.android.authn.common.AuthNModule
import com.americanexpress.android.autopay.AutopayModule
import com.americanexpress.android.benefits.BenefitsActivityTest
import com.americanexpress.android.benefits.BenefitsModule
import com.americanexpress.android.biometricsregistration.BiometricsRegistrationFlowTest
import com.americanexpress.android.biometricsregistration.cache.BiometricsCacheModule
import com.americanexpress.android.cardactivation.CardActivationRefactorModule
import com.americanexpress.android.cardactivation.cardenrollment.AddSuppCardFlowTest
import com.americanexpress.android.cardactivation.cardenrollment.CardEnrollmentTimelineRefreshFlowTest
import com.americanexpress.android.cardactivation.cardenrollment.DefaultCardFlowTest
import com.americanexpress.android.cardactivation.cardenrollment.DefaultCardSwitcherFlowTest
import com.americanexpress.android.cardactivation.cardenrollment.EnrollmentBackgroundTest
import com.americanexpress.android.cardactivation.cardenrollment.PostLoginCardEnrollmentFlowTest
import com.americanexpress.android.cardactivation.cardenrollment.PreLoginCardEnrollmentFlowTest
import com.americanexpress.android.cardactivation.cardenrollment.RemoveCardFlowTest
import com.americanexpress.android.cardactivation.cardenrollment.SetDefaultCardOptionFlowTest
import com.americanexpress.android.cardactivation.cardenrollment.TestCardEnrollmentModule
import com.americanexpress.android.cardactivation.cardenrollment.pin.CreatePinDuringRegistrationTest
import com.americanexpress.android.cardfreeze.CardFreezeActivityTest
import com.americanexpress.android.cardfreeze.CardFreezeFlowTest
import com.americanexpress.android.cardfreeze.CardFreezeModule
import com.americanexpress.android.cardfreeze.FreezeCardViewTest
import com.americanexpress.android.checkspendingpower.dagger.CheckSpendingPowerModule
import com.americanexpress.android.creditlineincrease.CreditLineIncreaseFlowTest
import com.americanexpress.android.creditlineincrease.CreditLineIncreaseModule
import com.americanexpress.android.dagger.PlanItAutoPayModule
import com.americanexpress.android.dagger.SalutationModule
import com.americanexpress.android.defaultcard.dagger.DefaultCardModule
import com.americanexpress.android.digitalchargeverification.CardVerificationFlowTest
import com.americanexpress.android.digitalchargeverification.DCVFraudConfirmedRobot
import com.americanexpress.android.digitalchargeverification.DCVFraudPushFlowTest
import com.americanexpress.android.digitalchargeverification.DCVFraudVerificationFlowTest
import com.americanexpress.android.dining.DiningFlowTest
import com.americanexpress.android.dining.DiningIntroActivityTest
import com.americanexpress.android.dining.booking.BookingConfirmedActivityTest
import com.americanexpress.android.dining.booking.BookingHistoryActivityTest
import com.americanexpress.android.dining.booking.BookingReviewActivityTest
import com.americanexpress.android.dining.citypicker.CityPickerActivityTest
import com.americanexpress.android.dining.dagger.DiningModule
import com.americanexpress.android.dining.featured.FeaturedRestaurantListActivityTest
import com.americanexpress.android.dining.home.DiningHomeActivityTest
import com.americanexpress.android.dining.restaurant.RestaurantDetailActivityTest
import com.americanexpress.android.dining.search.v2.DiningSearchFlowTest
import com.americanexpress.android.directdebit.DirectDebitFlowTest
import com.americanexpress.android.directdebit.DirectDebitModule
import com.americanexpress.android.directdebit.DirectDebitOverviewActivityTest
import com.americanexpress.android.disputes.DisputeCenterActivityTest
import com.americanexpress.android.disputes.DisputeCenterFlowTest
import com.americanexpress.android.disputes.DisputeOutcomeViewTest
import com.americanexpress.android.disputes.DisputeQuestionContainerViewTest
import com.americanexpress.android.disputes.DisputeQuestionFlowTest
import com.americanexpress.android.disputes.DisputesModule
import com.americanexpress.android.editemail.EditEmailActivityTest
import com.americanexpress.android.editemail.EditEmailFlowTest
import com.americanexpress.android.editemail.EditEmailModule
import com.americanexpress.android.editemail.EmailOutcomeViewTest
import com.americanexpress.android.editemail.EnterEmailViewTest
import com.americanexpress.android.editemail.SelectEmailViewTest
import com.americanexpress.android.editname.EditNameFlowTest
import com.americanexpress.android.editname.EditNameModule
import com.americanexpress.android.editphone.EditPhoneFlowTest
import com.americanexpress.android.editphone.EditPhoneViewTest
import com.americanexpress.android.errorhandling.network.ErrorMapperModule
import com.americanexpress.android.experimental.tests.BetaHubFlowTest
import com.americanexpress.android.intl.app.autopay.AutoPayEnrollmentReviewActivityTest
import com.americanexpress.android.experimental.tests.BetaLoginFlowTest
import com.americanexpress.android.experimental.utils.ExperimentalUtilsModule
import com.americanexpress.android.external.TestIntentResolverModule
import com.americanexpress.android.featureintros.FeatureIntrosFlowTest
import com.americanexpress.android.featureintros.screens.SinglePageFeatureIntroViewTest
import com.americanexpress.android.featureintros.smsreductionintro.SmsRedundancyModule
import com.americanexpress.android.financials.BreakdownActivityTest
import com.americanexpress.android.financials.FinancialsFlowTest
import com.americanexpress.android.financials.FinancialsModule
import com.americanexpress.android.financials.payflex.PayFlexActivityTest
import com.americanexpress.android.flavordimension.dagger.DimensionSpecificModules
import com.americanexpress.android.help.FaqLegalModule
import com.americanexpress.android.incomecapture.IncomeCaptureActivityTest
import com.americanexpress.android.intl.app.account.AccountTabEnableLocationTest
import com.americanexpress.android.intl.app.account.AccountTabEspressoTest
import com.americanexpress.android.intl.app.account.TestAccountTabDataHelper
import com.americanexpress.android.intl.app.amexpay.AmexPayTestRule
import com.americanexpress.android.intl.app.analytics.PznTrackingTestRule
import com.americanexpress.android.intl.app.analytics.TestAnalyticsTrackersModule
import com.americanexpress.android.intl.app.autopay.AutoPayConfirmationActivityTest
import com.americanexpress.android.intl.app.autopay.AutoPayEnrollmentActivityTest
import com.americanexpress.android.intl.app.autopay.AutoPayFlowTest
import com.americanexpress.android.intl.app.dagger.CardActivationModule
import com.americanexpress.android.intl.app.dagger.DebugMembershipClientModule
import com.americanexpress.android.intl.app.dagger.ExternalActionsModule
import com.americanexpress.android.intl.app.dagger.FeatureConfigRule
import com.americanexpress.android.intl.app.dagger.PredictiveSearchModule
import com.americanexpress.android.intl.app.dagger.ServicingModule
import com.americanexpress.android.intl.app.dagger.SessionManagementModule
import com.americanexpress.android.intl.app.dagger.SignalRelayModule
import com.americanexpress.android.intl.app.dagger.TestAmexPayModule
import com.americanexpress.android.intl.app.dagger.TestDigitalChargeVerificationModule
import com.americanexpress.android.intl.app.dagger.TestFeatureConfigModule
import com.americanexpress.android.intl.app.dagger.TestLoungeFinderModule
import com.americanexpress.android.intl.app.dagger.TestNotificationModule
import com.americanexpress.android.intl.app.dagger.TestPaymentFrameworkModule
import com.americanexpress.android.intl.app.dagger.TestPdfModule
import com.americanexpress.android.intl.app.dagger.TestPeerToPeerModule
import com.americanexpress.android.intl.app.dagger.TestPznTrackingModule
import com.americanexpress.android.intl.app.dagger.TestSsoCustomTabModule
import com.americanexpress.android.intl.app.dagger.WorkManagerModule
import com.americanexpress.android.intl.app.faq.CrisisCareFAQFlowTest
import com.americanexpress.android.intl.app.feature.checkspendingpower.SpendingPowerActivityTest
import com.americanexpress.android.intl.app.feature.checkspendingpower.SpendingPowerResultActivityTest
import com.americanexpress.android.intl.app.feature.loungefinder.LoungeFinderBookingFlowTest
import com.americanexpress.android.intl.app.feature.loungefinder.LoungeFinderDigitalMembershipCardFlowTest
import com.americanexpress.android.intl.app.feature.loungefinder.LoungeFinderHomeActivityTest
import com.americanexpress.android.intl.app.feature.loungefinder.LoungeFinderIntroFlowTest
import com.americanexpress.android.intl.app.feature.loungefinder.LoungePermissionFlowTest
import com.americanexpress.android.intl.app.feature.loungefinder.PrefetchDataViewTest
import com.americanexpress.android.intl.app.feature.loungefinder.PriorityPassDigitalMembershipCardEnabledRule
import com.americanexpress.android.intl.app.feature.loungefinder.geofence.GeofenceRegistrationTaskTest
import com.americanexpress.android.intl.app.feature.loungefinder.geofence.GeofenceTransitionHelperTest
import com.americanexpress.android.intl.app.feature.loungefinder.service.LoungeFinderClientImplTest
import com.americanexpress.android.intl.app.feature.loungefinder.terms.LoungeTermsActivityTest
import com.americanexpress.android.intl.app.feature.membergetmember.MemberGetMemberFlowTest
import com.americanexpress.android.intl.app.feature.payments.intl.IntlPaymentOutcomeActivityTest
import com.americanexpress.android.intl.app.feature.payments.intl.IntlPaymentReviewActivityEspressoTest
import com.americanexpress.android.intl.app.feature.payments.intl.PaymentFlowTest
import com.americanexpress.android.intl.app.feature.payments.intl.PaymentOptionsCaTest
import com.americanexpress.android.intl.app.feature.payments.intl.PaymentOptionsHeaderMessagesTest
import com.americanexpress.android.intl.app.feature.payments.intl.PaymentOptionsPaymentAmountTest
import com.americanexpress.android.intl.app.feature.payments.intl.PaymentOptionsPaymentMethodsGbTest
import com.americanexpress.android.intl.app.feature.payments.intl.PaymentOptionsPaymentMethodsMxTest
import com.americanexpress.android.intl.app.feature.payments.intl.PaymentOptionsPaymentMethodsRobot
import com.americanexpress.android.intl.app.feature.referafriend.ReferAFriendOptionsTestRule
import com.americanexpress.android.intl.app.feature.referafriend.ReferAFriendSendEmailActivityTest
import com.americanexpress.android.intl.app.feature.referafriendv2.ReferAFriendInfoActivityTest
import com.americanexpress.android.intl.app.feature.referafriendv2.ReferAFriendV2ActivityTest
import com.americanexpress.android.intl.app.feature.referafriendv2.ReferAFriendV2FlowTest
import com.americanexpress.android.intl.app.feature.referafriendv2.ShareViaAmexActivityTest
import com.americanexpress.android.intl.app.feature.referafriendv2.ShareViaAmexErrorActivityTest
import com.americanexpress.android.intl.app.feature.referafriendv2.ShareViaAmexSuccessActivityTest
import com.americanexpress.android.intl.app.feature.servicing.ProminentEntryFlowTest
import com.americanexpress.android.intl.app.feature.servicing.ServicingActivityTest
import com.americanexpress.android.intl.app.feature.servicing.callus.CallUsActivityTest
import com.americanexpress.android.intl.app.feature.servicing.crisiscare.CrisisCareServicingFlowTest
import com.americanexpress.android.intl.app.feature.servicing.di.ServicingCardModule
import com.americanexpress.android.intl.app.featureintros.FeatureIntroTestRule
import com.americanexpress.android.intl.app.featureintros.TestFeatureIntrosModule
import com.americanexpress.android.intl.app.featureintros.testexperimentalprefsstoragemodule.TestExperimentalPrefsStorageModule
import com.americanexpress.android.intl.app.paymenthistory.PaymentHistoryActivityTest
import com.americanexpress.android.intl.app.services.FCMListenerServiceTest
import com.americanexpress.android.intl.app.statements.StatementDetailRobot
import com.americanexpress.android.intl.app.statements.StatementDetailsActivityTest
import com.americanexpress.android.intl.app.statements.StatementsActivityTest
import com.americanexpress.android.intl.app.transactiondetails.TransactionDetailsActivityTest
import com.americanexpress.android.intl.app.transactiondetails.TransactionDetailsTransactionTypeTest
import com.americanexpress.android.intl.app.view.activity.BottomTabsFlowTest
import com.americanexpress.android.intl.app.view.activity.ChangePasswordFlowTest
import com.americanexpress.android.intl.app.view.activity.DeeplinkingFlow2Test
import com.americanexpress.android.intl.app.view.activity.DeeplinkingFlowTest
import com.americanexpress.android.intl.app.view.activity.EulaBypassTest
import com.americanexpress.android.intl.app.view.activity.EulaFlowTest
import com.americanexpress.android.intl.app.view.activity.FinancialTabViewTest
import com.americanexpress.android.intl.app.view.activity.InitializationDataRobot.FaqLegalRepositoryInitializer
import com.americanexpress.android.intl.app.view.activity.OverviewTabTest
import com.americanexpress.android.intl.app.view.activity.PocketLoopStateRobot
import com.americanexpress.android.intl.app.view.activity.SplashScreenInvalidCertificateTest
import com.americanexpress.android.intl.app.view.fingerprintlogin.FingerprintDialogRobot
import com.americanexpress.android.intl.app.view.fingerprintlogin.FingerprintLoginTest
import com.americanexpress.android.intl.app.view.fingerprintlogin.FingerprintSensorRobot
import com.americanexpress.android.intl.app.view.fingerprintlogin.FingerprintSettingsTest
import com.americanexpress.android.intl.app.view.login.LoginAnimationTest
import com.americanexpress.android.intl.app.view.login.LoginFlowTest
import com.americanexpress.android.intl.app.view.login.LoginWithNoLinkedCardsFlowTest
import com.americanexpress.android.intl.app.view.login.OneStreamLoginTest
import com.americanexpress.android.intl.app.view.offers.AddOffersFlowTest
import com.americanexpress.android.intl.app.view.offers.GlobalOffersFlowTest
import com.americanexpress.android.intl.app.view.offers.OffersFlowTest
import com.americanexpress.android.intl.app.view.offers.OffersImpressionsTest
import com.americanexpress.android.intl.app.view.pushnotifications.AirportNotificationFlowTest
import com.americanexpress.android.intl.app.view.pushnotifications.CardNotificationsActivityTest
import com.americanexpress.android.intl.app.view.pushnotifications.CardNotificationsDetailActivityForFRTest
import com.americanexpress.android.intl.app.view.pushnotifications.CardNotificationsDetailActivityTest
import com.americanexpress.android.intl.app.view.timeline.CardSwitcherRefreshTest
import com.americanexpress.android.intl.app.view.timeline.CardSwitcherTest
import com.americanexpress.android.intl.app.view.timeline.TimelineFlowTest
import com.americanexpress.android.intl.app.view.timeline.TimelineOffersFlowTest
import com.americanexpress.android.intl.app.view.timeline.TimelinePlanItExperimentBadgeTest
import com.americanexpress.android.intl.app.view.twosteplogin.TwoStepLoginFlowTest
import com.americanexpress.android.language.LanguageSelectionFlowTest
import com.americanexpress.android.languageselection.dagger.LocaleSelectionScreenModule
import com.americanexpress.android.localchampion.dagger.LocalChampionModule
import com.americanexpress.android.login.networking.dagger.DebugLoginNetworkingModule
import com.americanexpress.android.loungefinder.dagger.LoungeFinderLocalModule
import com.americanexpress.android.loungefinder.service.MeldLoungeFinderModule
import com.americanexpress.android.maintab.model.MainTabCardModule
import com.americanexpress.android.membergetmember.MemberGetMemberModule
import com.americanexpress.android.membership.CashbackFlowTest
import com.americanexpress.android.membership.GiftCardWalletActivityTest
import com.americanexpress.android.membership.GlobalMembershipFlowTest
import com.americanexpress.android.membership.GlobalMembershipTabViewTest
import com.americanexpress.android.membership.MembershipModule
import com.americanexpress.android.membership.RedeemIntroViewTest
import com.americanexpress.android.membership.RewardsViewTest
import com.americanexpress.android.membership.bonusdetail.BonusDetailActivityTest
import com.americanexpress.android.membership.dagger.MembershipLinkModule
import com.americanexpress.android.membership.extrabenefit.ExtraPointsBenefitModule
import com.americanexpress.android.membership.extrabenefit.PointsTrackerActivityTest
import com.americanexpress.android.membership.extrabenefit.TransactionsActivityTest
import com.americanexpress.android.membership.membershipdetail.MembershipDetailActivityTest
import com.americanexpress.android.messaging.MessagingFlowTest
import com.americanexpress.android.messaging.MessagingPushIntroFlowTest
import com.americanexpress.android.model.initialization.Initialization
import com.americanexpress.android.model.initialization.InitializationDataModule
import com.americanexpress.android.ndlfaq.NdlFaqActivityTest
import com.americanexpress.android.ndlfaq.NdlFaqModule
import com.americanexpress.android.oauthgrant.OAuthGrantFlowTest
import com.americanexpress.android.oauthgrant.OauthGrantAccessActivityTest
import com.americanexpress.android.offers.OffersModule
import com.americanexpress.android.otp.OtpFlowTest
import com.americanexpress.android.otp.OtpResultTest
import com.americanexpress.android.paperless.PaperlessActivityTest
import com.americanexpress.android.payments.Payment3DSViewTest
import com.americanexpress.android.payments.PaymentsActivityTest
import com.americanexpress.android.payments.PaymentsModule
import com.americanexpress.android.payments.networking.PaymentsNetworkingModule
import com.americanexpress.android.paymentsoptions.PaymentsOptionsModule
import com.americanexpress.android.payovertime.PayflexPreferences
import com.americanexpress.android.payovertime.dagger.PayOverTimeModule
import com.americanexpress.android.payovertime.revolve.RevolveTransactionFlowTest
import com.americanexpress.android.payovertime.summary.PayflexHubFlowTest
import com.americanexpress.android.payovertime.summary.PayflexHubSummaryViewTest
import com.americanexpress.android.payovertime.summary.TogglePayflexPlansViewTest
import com.americanexpress.android.payovertime.summary.view.DeferredTransactionsViewTest
import com.americanexpress.android.payyourway.CreatePlanTest
import com.americanexpress.android.payyourway.PlanManagementFlowTest
import com.americanexpress.android.payyourway.PlanManagementRobot
import com.americanexpress.android.payyourway.PlanManagementTest
import com.americanexpress.android.payyourway.dagger.DeferredPaymentsModule
import com.americanexpress.android.peertopeer.M1_45095_ETD_Hub_Enrollment_BackNavigationTest
import com.americanexpress.android.peertopeer.PartnerLinkFlowTest
import com.americanexpress.android.peertopeer.PeerToPeerDeepLinkFlowTest
import com.americanexpress.android.peertopeer.PeerToPeerEnrollmentFlowTest
import com.americanexpress.android.peertopeer.PeerToPeerHubFlowTest
import com.americanexpress.android.peertopeer.PeerToPeerNavigationFlowTest
import com.americanexpress.android.peertopeer.SendFlowTest
import com.americanexpress.android.peertopeer.SplitFlowTest
import com.americanexpress.android.peertopeer.settings.DelinkPartnerFlowTest
import com.americanexpress.android.peertopeer.settings.SettingsFlowTest
import com.americanexpress.android.peertopeer.settings.UpdateDefaultCardFlowTest
import com.americanexpress.android.peertopeer.split.SplitCustomContactsFlowTest
import com.americanexpress.android.pinmanagement.PinManagementCapabilityTest
import com.americanexpress.android.pinmanagement.PinManagementChangePinTest
import com.americanexpress.android.pinmanagement.PinManagementChoosePinTest
import com.americanexpress.android.pinmanagement.PinManagementModule
import com.americanexpress.android.pinmanagement.PinManagementPinEligibilityTest
import com.americanexpress.android.pinmanagement.PinManagementUnlockPinTest
import com.americanexpress.android.pinmanagement.PinManagementViewPinTest
import com.americanexpress.android.pinmanagement.choose.refactor.dagger.ChoosePinModule
import com.americanexpress.android.planitV2.CreatePlanFromTransactionFlowTest
import com.americanexpress.android.planitV2.PlanItActivityTest
import com.americanexpress.android.planitV2.PlanItFaqFlowTest
import com.americanexpress.android.planitV2.PlanItFlowTest
import com.americanexpress.android.planitV2.PlanItHtmlTermsActivityTest
import com.americanexpress.android.planitV2.PlanPendingTimelineTest
import com.americanexpress.android.predictivesearch.filters.panel.multiselection.MultiSelectionFilterActivityTest
import com.americanexpress.android.predictivesearch.result.map.ResultMapViewTest
import com.americanexpress.android.profile.ProfileFlowTest
import com.americanexpress.android.profile.ProfileHomeViewTest
import com.americanexpress.android.profile.ProfileModule
import com.americanexpress.android.referafriend.dagger.TestReferAFriendModule
import com.americanexpress.android.referafriendv2.dagger.ReferAFriendV2Module
import com.americanexpress.android.referafriendv2.dagger.ShareOptionCheckerModule
import com.americanexpress.android.regressionTest.BaseFlowTestFromSplashScreen
import com.americanexpress.android.regressionTest.BaseFlowWithLocationTestFromSplashScreen
import com.americanexpress.android.statements.StatementModule
import com.americanexpress.android.test.TestAssetLoader
import com.americanexpress.android.test.support.SessionLoader
import com.americanexpress.android.test.wiremock.flows.PlanManagementFlow
import com.americanexpress.android.timelineclient.dagger.TimelineClientModule
import com.americanexpress.android.transactiondetails.TransactionDetailsModule
import com.americanexpress.android.transactionsearch.TransactionSearchActivityTest
import com.americanexpress.android.transactionsearch.TransactionSearchModule
import com.americanexpress.android.usepoints.UsePointsActivityTest
import com.americanexpress.android.usepoints.UsePointsRobot
import com.americanexpress.android.userregistration.ui.UserRegistrationModule
import com.americanexpress.android.view.webview.WebViewModule
import com.americanexpress.android.widget.money.MoneyEditTextFrCATests
import com.americanexpress.android.widget.money.MoneyEditTextGBTests
import com.americanexpress.android.widget.money.MoneyEditTextIntegerModeDefaultZeroTests
import com.americanexpress.android.widget.money.MoneyEditTextIntegerModeNoDefaultZeroTests
import com.americanexpress.android.widget.money.MoneyEditTextJPTests
import com.americanexpress.android.widget.money.MoneyEditTextUSTests
import com.americanexpress.cardactivation.refactor.CardScanButtonTest
import com.americanexpress.cardactivation.refactor.PostLoginCardActivationFlowTest
import com.americanexpress.cardactivation.refactor.PreLoginCardActivationFlowTest
import com.americanexpress.cardactivation.refactor.PreLoginUserRegistrationFlowTest
import com.americanexpress.pushnotifications.dagger.PushNotificationsModule
import com.americanexpress.timeline.dagger.TimelineModule
import com.americanexpress.us.msl.autopay.MeldAutoPayModule
import com.americanexpress.us.msl.contactus.MeldContactUsModule
import com.americanexpress.us.msl.rewards.MissileRewardsModule
import com.americanexpress.us.msl.sso.SsoWebLinkModule
import com.example.askamexsample.checkout.CheckoutHomeViewTest
import com.example.askamexsample.checkout.CostBreakdownViewTest
import com.example.askamexsample.checkout.FareRulesAndBaggageFeesViewTest
import com.example.askamexsample.checkout.ManageContactViewTest
import com.example.askamexsample.checkout.ManagePassportViewTest
import com.example.askamexsample.checkout.PolicyAndRestrictionViewTest
import com.example.askamexsample.checkout.SendReceiptViewTest
import dagger.BindsInstance
import dagger.Subcomponent
import io.aexp.otp.networking.OtpNetworkingModule
import io.aexp.payovertimev2.dagger.PayOverTimeV2Module
import io.aexp.peertopeer.PeerToPeerAndroidModule
import io.aexp.peertopeer.networking.Peer2PeerNetworkingModule
import io.aexp.peertopeer.shared.ui.cache.PeerToPeerCacheModule
import io.aexp.planitV2.ui.viewplans.dagger.PlanItModule

@InitializedScope
@Subcomponent(
    modules = [
        AppCardModule::class,
        AuthNModule::class,
        AutopayModule::class,
        BenefitsModule::class,
        CardActivationModule::class,
        CardActivationRefactorModule::class,
        CardFreezeModule::class,
        CardReplacementModule::class,
        CheckSpendingPowerModule::class,
        ChoosePinModule::class,
        CreditLineIncreaseModule::class,
        BiometricsCacheModule::class,
        DebugLoginNetworkingModule::class,
        DebugMembershipClientModule::class,
        DefaultCardModule::class,
        DeferredPaymentsModule::class,
        DimensionSpecificModules::class,
        DiningModule::class,
        DirectDebitModule::class,
        DisputesModule::class,
        EditEmailModule::class,
        EditNameModule::class,
        ErrorMapperModule::class,
        ExperimentalUtilsModule::class,
        ExternalActionsModule::class,
        ExtraPointsBenefitModule::class,
        ExtraPointsBenefitModule::class,
        FaqLegalModule::class,
        FinancialsModule::class,
        InAuthModule::class,
        InitializationDataModule::class,
        LocalChampionModule::class,
        LoungeFinderLocalModule::class,
        MainTabCardModule::class,
        MakePaymentModule::class,
        MeldAutoPayModule::class,
        MeldContactUsModule::class,
        MeldLoungeFinderModule::class,
        MemberGetMemberModule::class,
        MembershipLinkModule::class,
        MembershipModule::class,
        MissileRewardsModule::class,
        NdlFaqModule::class,
        OffersModule::class,
        OtpNetworkingModule::class,
        PaymentsModule::class,
        PaymentsNetworkingModule::class,
        PaymentsOptionsModule::class,
        PaymentsUsModule::class,
        PayOverTimeModule::class,
        PayOverTimeV2Module::class,
        Peer2PeerNetworkingModule::class,
        PeerToPeerAndroidModule::class,
        PeerToPeerCacheModule::class,
        io.aexp.peertopeer.v2.shared.ui.cache.PeerToPeerCacheModule::class,
        PinManagementModule::class,
        PlanItAutoPayModule::class,
        PlanItModule::class,
        PredictiveSearchModule::class,
        ProfileModule::class,
        PushNotificationsModule::class,
        ReferAFriendV2Module::class,
        SalutationModule::class,
        ServicingModule::class,
        ServicingCardModule::class,
        SignalRelayModule::class,
        SmsRedundancyModule::class,
        SsoWebLinkModule::class,
        StatementModule::class,
        StatementsV2Module::class,
        TestAmexPayModule::class,
        TestAnalyticsTrackersModule::class,
        TestCardEnrollmentModule::class,
        TestCbisModule::class,
        TestDigitalChargeVerificationModule::class,
        TestExperimentalPrefsStorageModule::class,
        TestFeatureConfigModule::class,
        TestFeatureIntrosModule::class,
        TestFingerprintModule::class,
        TestLoungeFinderModule::class,
        TestMessageUsComponentsModule::class,
        TestNotificationModule::class,
        TestOneStreamModule::class,
        TestPaymentFrameworkModule::class,
        TestPdfModule::class,
        TestPeerToPeerModule::class,
        WorkManagerModule::class,
        TestPznTrackingModule::class,
        TestReferAFriendModule::class,
        ShareOptionCheckerModule::class,
        TestSsoCustomTabModule::class,
        TimelineClientModule::class,
        TimelineModule::class,
        TransactionDetailsModule::class,
        UserRegistrationModule::class,
        WebViewModule::class,
        TransactionSearchModule::class,
        LocaleSelectionScreenModule::class,
        TestIntentResolverModule::class,
        SessionManagementModule::class,
    ]
)
interface DummyTestInitializedComponent : InitializedComponent {

    fun inject(repo: FaqLegalRepositoryInitializer)

    fun inject(rule: FeatureConfigRule)

    fun inject(robot: PocketLoopStateRobot)

    fun inject(robot: FingerprintSensorRobot)

    fun inject(robot: FingerprintDialogRobot)

    fun inject(test: LoungePermissionFlowTest)

    fun inject(test: LoungeFinderHomeActivityTest)

    fun inject(test: LoungeFinderIntroFlowTest)

    fun inject(test: LoungeFinderDigitalMembershipCardFlowTest)

    fun inject(test: LoungeTermsActivityTest)

    fun inject(test: GeofenceTransitionHelperTest)

    fun inject(test: GeofenceRegistrationTaskTest)

    fun inject(test: LoungeFinderClientImplTest)

    fun inject(loader: SessionLoader)

    fun inject(spendingPowerActivityTest: SpendingPowerActivityTest)

    fun inject(spendingPowerResultActivityTest: SpendingPowerResultActivityTest)

    fun inject(moneyEditTextFrCATests: MoneyEditTextFrCATests)

    fun inject(moneyEditTextGBTests: MoneyEditTextGBTests)

    fun inject(moneyEditTextJPTests: MoneyEditTextJPTests)

    fun inject(moneyEditTextJPTests: MoneyEditTextIntegerModeDefaultZeroTests)

    fun inject(moneyEditTextJPTests: MoneyEditTextIntegerModeNoDefaultZeroTests)

    fun inject(moneyEditTextUSTests: MoneyEditTextUSTests)

    fun inject(rule: ReferAFriendOptionsTestRule)

    fun inject(test: ReferAFriendSendEmailActivityTest)

    fun inject(test: PointsTrackerActivityTest)

    fun inject(test: TransactionsActivityTest)

    fun inject(test: MemberGetMemberFlowTest)

    fun inject(test: AutoPayConfirmationActivityTest)

    fun inject(test: CreatePlanFromTransactionFlowTest)

    fun inject(test: PlanItFaqFlowTest)

    fun inject(test: PlanItHtmlTermsActivityTest)

    fun inject(test: GiftCardWalletActivityTest)

    fun inject(test: AutoPayEnrollmentActivityTest)

    fun inject(test: AutoPayEnrollmentReviewActivityTest)

    fun inject(test: UsePointsActivityTest)

    fun inject(test: AccountTabEspressoTest)

    fun inject(test: AccountTabEnableLocationTest)

    fun inject(test: PaymentOptionsHeaderMessagesTest)

    fun inject(test: PaymentOptionsPaymentMethodsGbTest)

    fun inject(test: PaymentOptionsPaymentMethodsMxTest)

    fun inject(test: PaymentOptionsCaTest)

    fun inject(robot: PaymentOptionsPaymentMethodsRobot)

    fun inject(test: RemoveCardFlowTest)

    fun inject(test: AddSuppCardFlowTest)

    fun inject(loader: TestAssetLoader)

    fun inject(test: PaymentHistoryActivityTest)

    fun inject(test: FinancialsFlowTest)

    fun inject(test: StatementDetailsActivityTest)

    fun inject(robot: StatementDetailRobot)

    fun inject(robot: UsePointsRobot)

    fun inject(test: StatementsActivityTest)

    fun inject(test: MakePaymentSummaryActivityTest)

    fun inject(test: MakePaymentSummaryActivity2Test)

    fun inject(test: MakePaymentConfirmActivityTest)

    fun inject(test: MakePaymentConfirmedActivityTest)

    fun inject(test: ScheduledPaymentDetailsActivityTest)

    fun inject(test: MultipleScheduledPaymentsActivityTest)

    fun inject(rule: PaymentTestRule)

    fun inject(base: MakePaymentFlowTestBase)

    fun inject(test: MakePaymentFlow1Test)

    fun inject(test: MakePaymentFlow2Test)

    fun inject(test: MakePaymentFlow3Test)

    fun inject(test: MakePaymentFlow5Test)

    fun inject(test: MakePaymentFlow6Test)

    fun inject(test: ScheduledPaymentDetailsActivityFlowTest)

    fun inject(test: CardNotificationsActivityTest)

    fun inject(test: CardNotificationsDetailActivityTest)

    fun inject(test: CardNotificationsDetailActivityForFRTest)

    fun inject(test: AirportNotificationFlowTest)

    fun inject(test: FingerprintSettingsTest)

    fun inject(test: FingerprintLoginTest)

    fun inject(test: CardSwitcherTest)

    fun inject(helper: TestAccountTabDataHelper)

    fun inject(test: OffersFlowTest)

    fun inject(test: AddOffersFlowTest)

    fun inject(test: AutoPayFlowTest)

    fun inject(test: TransactionDetailsActivityTest)

    fun inject(test: TransactionDetailsTestRule)

    fun inject(test: ReceiptCaptureTest)

    fun inject(test: TransactionDetailsTransactionTypeTest)

    fun inject(test: ServicingActivityTest)

    fun inject(test: CallUsActivityTest)

    fun inject(test: PayFlexActivityTest)

    fun inject(test: PayflexHubFlowTest)

    fun inject(test: PlanItFlowTest)

    fun inject(test: PlanPendingTimelineTest)

    fun inject(test: com.americanexpress.android.planitV2.CreatePlanFlowTest)

    fun inject(test: PlanItActivityTest)

    fun inject(test: PayflexHubSummaryViewTest)

    fun inject(test: RevolveTransactionFlowTest)

    fun inject(test: TogglePayflexPlansViewTest)

    fun inject(test: EulaFlowTest)

    fun inject(test: IntlPaymentOutcomeActivityTest)

    fun inject(test: CardVerificationFlowTest)

    fun inject(test: DCVFraudVerificationFlowTest)

    fun inject(test: DCVFraudPushFlowTest)

    fun inject(robot: DCVFraudConfirmedRobot)

    fun inject(test: BottomTabsFlowTest)

    fun inject(test: DiningHomeActivityTest)

    fun inject(test: CityPickerActivityTest)

    fun inject(test: DiningSearchFlowTest)

    fun inject(test: MembershipDetailActivityTest)

    fun inject(test: BonusDetailActivityTest)

    fun inject(test: EulaBypassTest)

    fun inject(test: ChangePasswordFlowTest)

    fun inject(rule: FeatureIntroTestRule)

    fun inject(test: TwoStepLoginFlowTest)

    fun inject(test: TimelineFlowTest)

    fun inject(test: TimelinePlanItExperimentBadgeTest)

    fun inject(test: TimelineOffersFlowTest)

    fun inject(test: PostLoginCardEnrollmentFlowTest)

    fun inject(test: FeatureIntrosFlowTest)

    fun inject(test: IntlPaymentReviewActivityEspressoTest)

    fun inject(rule: AmexPayTestRule)

    fun inject(amexPayFlowTest: AmexPayFlowTest)

    fun inject(amexPayErrorTest: AmexPayErrorTest)

    fun inject(amexPayErrorContentTest: AmexPayErrorContentTest)

    fun inject(amexPayProvisioningFlowTest: AmexPayProvisioningFlowTest)

    fun inject(amexPayFlowTest: AmexPayBarcodePaymentFlowTest)

    fun inject(amexPayLoginFragmentTest: AmexPayLoginFragmentTest)

    fun inject(test: BarcodePaymentActivityTest)

    fun inject(test: AmexPayBarcodePaymentNoPinFlowTest)

    fun inject(test: TapNPayActivityTest)

    fun inject(test: DirectDebitFlowTest)

    fun inject(test: DirectDebitOverviewActivityTest)

    fun inject(test: PaymentOptionsPaymentAmountTest)

    fun inject(test: PaymentFlowTest)

    fun inject(test: PreLoginCardEnrollmentFlowTest)

    fun inject(test: EnrollmentBackgroundTest)

    fun inject(test: CreatePinDuringRegistrationTest)

    fun inject(test: CardEnrollmentTimelineRefreshFlowTest)

    fun inject(test: LoginFlowTest)

    fun inject(test: OneStreamLoginTest)

    fun inject(test: LoginAnimationTest)

    fun inject(test: BetaLoginFlowTest)

    fun inject(test: BetaHubFlowTest)

    fun inject(planManagementTest: PlanManagementTest)

    fun inject(createPlanTest: CreatePlanTest)

    fun inject(planManagementFlowTest: PlanManagementFlowTest)

    fun inject(planManagementRobot: PlanManagementRobot)

    fun inject(planManagementFlow: PlanManagementFlow)

    fun inject(test: DeeplinkingFlowTest)

    fun inject(test: RestaurantDetailActivityTest)

    fun inject(test: BookingReviewActivityTest)

    fun inject(test: BookingConfirmedActivityTest)

    fun inject(test: FeaturedRestaurantListActivityTest)

    fun inject(test: BookingHistoryActivityTest)

    fun inject(test: OverviewTabTest)

    fun inject(test: DiningFlowTest)

    fun inject(deeplinkingFlow2Test: DeeplinkingFlow2Test)

    fun inject(test: ResultMapViewTest)

    fun inject(test: DisputeCenterFlowTest)

    fun inject(test: DisputeCenterActivityTest)

    fun inject(test: DisputeQuestionContainerViewTest)

    fun inject(test: DisputeOutcomeViewTest)

    fun inject(test: DisputeQuestionFlowTest)

    fun inject(test: EnterEmailViewTest)

    fun inject(test: EmailOutcomeViewTest)

    fun inject(test: EditEmailFlowTest)

    fun inject(test: FreezeCardViewTest)

    fun inject(test: SelectEmailViewTest)

    fun inject(test: PinManagementCapabilityTest)

    fun inject(test: PinManagementPinEligibilityTest)

    fun inject(test: PinManagementViewPinTest)

    fun inject(test: PinManagementUnlockPinTest)

    fun inject(test: PinManagementChangePinTest)

    fun inject(test: PinManagementChoosePinTest)

    fun inject(prominentEntryFlowTest: ProminentEntryFlowTest)

    fun inject(test: BenefitsActivityTest)

    fun inject(test: GlobalMembershipFlowTest)

    fun inject(test: GlobalOffersFlowTest)

    fun inject(test: MessagingFlowTest)

    fun inject(test: MessagingPushIntroFlowTest)

    fun inject(test: RedeemIntroViewTest)

    fun inject(test: OffersImpressionsTest)

    fun inject(test: RewardsViewTest)

    fun inject(test: LoginWithNoLinkedCardsFlowTest)

    fun inject(test: CashbackFlowTest)

    fun inject(activityTest: ProvisionedCardsActivityTest)

    fun inject(test: FCMListenerServiceTest)

    fun inject(test: SplashScreenInvalidCertificateTest)

    fun inject(test: PaperlessActivityTest)

    fun inject(test: EditEmailActivityTest)

    fun inject(test: NdlFaqActivityTest)

    fun inject(test: SetDefaultCardOptionFlowTest)

    fun inject(test: DefaultCardFlowTest)

    fun inject(test: DefaultCardSwitcherFlowTest)

    fun inject(test: CardFreezeFlowTest)

    fun inject(test: CardFreezeActivityTest)

    fun inject(test: AmexPayProvisioningSuccessViewTest)

    fun inject(test: AmexPayProvisioningLocationPermissionIntroViewTest)

    fun inject(prefetchDataViewTest: PrefetchDataViewTest)

    fun inject(test: ProfileHomeViewTest)

    fun inject(test: ProfileFlowTest)

    fun inject(test: MultiSelectionFilterActivityTest)

    fun inject(test: SinglePageFeatureIntroViewTest)

    fun inject(test: LoungeFinderBookingFlowTest)

    fun inject(referAFriendActivityTest: ReferAFriendV2ActivityTest)

    fun inject(referAFriendFlowTest: ReferAFriendV2FlowTest)

    fun inject(referAFriendInfoActivityTest: ReferAFriendInfoActivityTest)

    fun inject(shareViaAmexActivityTest: ShareViaAmexActivityTest)

    fun inject(shareViaAmexErrorActivityTest: ShareViaAmexErrorActivityTest)

    fun inject(activityTest: BreakdownActivityTest)

    fun inject(test: PeerToPeerDeepLinkFlowTest)
    fun inject(test: com.americanexpress.android.peertopeerV2.PeerToPeerDeepLinkFlowTest)

    fun inject(test: PeerToPeerEnrollmentFlowTest)
    fun inject(test: com.americanexpress.android.peertopeerV2.PeerToPeerEnrollmentFlowTest)

    fun inject(test: PeerToPeerNavigationFlowTest)
    fun inject(test: com.americanexpress.android.peertopeerV2.PeerToPeerNavigationFlowTest)

    fun inject(test: PartnerLinkFlowTest)
    fun inject(test: com.americanexpress.android.peertopeerV2.PartnerLinkFlowTest)

    fun inject(test: SendFlowTest)

    fun inject(test: SplitFlowTest)
    fun inject(test: com.americanexpress.android.peertopeerV2.SplitFlowTest)

    fun inject(test: SplitCustomContactsFlowTest)
    fun inject(test: com.americanexpress.android.peertopeerV2.split.SplitCustomContactsFlowTest)

    fun inject(rule: PriorityPassDigitalMembershipCardEnabledRule)

    fun inject(test: PeerToPeerHubFlowTest)
    fun inject(test: com.americanexpress.android.peertopeerV2.PeerToPeerHubFlowTest)

    fun inject(test: SettingsFlowTest)
    fun inject(test: com.americanexpress.android.peertopeerV2.settings.SettingsFlowTest)

    fun inject(test: UpdateDefaultCardFlowTest)
    fun inject(test: com.americanexpress.android.peertopeerV2.settings.UpdateDefaultCardFlowTest)

    fun inject(test: DelinkPartnerFlowTest)
    fun inject(test: com.americanexpress.android.peertopeerV2.settings.DelinkPartnerFlowTest)

    fun inject(test: CardSwitcherRefreshTest)

    fun inject(editAddressViewFromProfileTest: EditAddressViewFromProfileTest)

    fun inject(editAddressViewFromRecommendedTest: EditAddressViewFromRecommendedTest)

    fun inject(test: EditNameFlowTest)

    fun inject(test: RecommendedAddressViewTest)

    fun inject(test: ConfirmationViewTest)

    fun inject(incomeCaptureActivityTest: IncomeCaptureActivityTest)

    fun inject(creditLineIncreaseFlowTest: CreditLineIncreaseFlowTest)

    fun inject(payflexPrefs: PayflexPreferences)

    fun inject(test: OtpResultTest)

    fun inject(test: OtpFlowTest)

    fun inject(test: DeferredTransactionsViewTest)

    fun inject(activityTest: ShareViaAmexSuccessActivityTest)

    fun inject(test: M1_45095_ETD_Hub_Enrollment_BackNavigationTest)
    fun inject(test: com.americanexpress.android.peertopeerV2.M1_45095_ETD_Hub_Enrollment_BackNavigationTest)

    fun inject(test: EditPhoneViewTest)

    fun inject(test: com.americanexpress.android.editphone.ConfirmationViewTest)

    fun inject(test: PreLoginCardActivationFlowTest)

    fun inject(test: PreLoginUserRegistrationFlowTest)

    fun inject(test: PostLoginCardActivationFlowTest)

    fun inject(test: CardScanButtonTest)

    fun inject(diningIntroActivityTest: DiningIntroActivityTest)

    fun inject(rule: PznTrackingTestRule)

    fun inject(test: LogoffTest)

    fun inject(test: TransactionSearchActivityTest)

    fun inject(test: LanguageSelectionFlowTest)

    fun inject(test: BiometricsRegistrationFlowTest)

    fun inject(test: OauthGrantAccessActivityTest)

    fun inject(test: OAuthGrantFlowTest)

    fun inject(test: ManageContactViewTest)

    fun inject(test: GlobalMembershipTabViewTest)

    fun inject(test: ManagePassportViewTest)

    fun inject(test: EditPhoneFlowTest)

    fun inject(test: CheckoutHomeViewTest)

    fun inject(test: CostBreakdownViewTest)

    fun inject(test: SendReceiptViewTest)

    fun inject(test: FareRulesAndBaggageFeesViewTest)

    fun inject(test: PolicyAndRestrictionViewTest)

    fun inject(test: BaseFlowTestFromSplashScreen)

    fun inject(test: BaseFlowWithLocationTestFromSplashScreen)

    fun inject(test: CrisisCareServicingFlowTest)

    fun inject(test: CrisisCareFAQFlowTest)

    fun inject(test: PaymentsActivityTest)

    fun inject(test: Payment3DSViewTest)

    fun inject(test: FinancialTabViewTest)

    @Subcomponent.Builder
    interface Builder {
        fun inAuthModule(module: InAuthModule): Builder

        fun fingerprintModule(module: TestFingerprintModule): Builder

        fun cbisModule(module: TestCbisModule): Builder

        @BindsInstance
        fun initialization(initialization: Initialization): Builder

        fun build(): DummyTestInitializedComponent
    }
}
